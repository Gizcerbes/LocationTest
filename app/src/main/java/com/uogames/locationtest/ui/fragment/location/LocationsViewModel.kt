package com.uogames.locationtest.ui.fragment.location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.domain.entity.Location
import com.uogames.locationtest.domain.entity.Section
import com.uogames.locationtest.domain.interfaces.DatabaseRepository
import com.uogames.locationtest.domain.interfaces.ImageRepository
import com.uogames.locationtest.domain.interfaces.NetworkRepository
import com.uogames.locationtest.utlis.observe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

typealias LocationID = Int

@HiltViewModel
class LocationsViewModel @Inject constructor(
	private val fileProvider: ImageRepository,
	private val databaseRepository: DatabaseRepository,
	private val networkRepository: NetworkRepository
) : ViewModel() {

	val isPickingImage = MutableStateFlow(false)

	val locationName = MutableStateFlow("")
	val sectionName = MutableStateFlow("")

	val editMode = MutableStateFlow(false)

	val sectionID = MutableStateFlow(1)

	val locationForADD = MutableStateFlow<LocationID?>(null)

	private val section = MutableStateFlow<Section?>(null)
	private val editLocale = HashMap<Locale, Boolean>()
	private val selectImage = HashMap<ImageInfo, Boolean>()

	//private val _itemList = ArrayList<ItemSelect<Pair<Uri, ImageInfo>>>()

	private val _isLoading = MutableStateFlow(false)
	val isLoading = _isLoading.asStateFlow()

	val adapter = ImagePickerAdapter(
		callSize = { runCatching { section.value?.locations?.firstOrNull()?.imageInfo?.size }.getOrNull() ?: 0 },
		callItem = { runCatching { section.value?.locations?.firstOrNull()?.imageInfo?.get(it) }.getOrNull() },
		longClick = { editMode.value = true },
		isEditMode = editMode,
		selectItem = { item, b -> selectImage[item] = b },
		isSelected = { selectImage[it] ?: false},
		callImageLink = { networkRepository.getImageUri(it.uuid) }
	)

	init {
		viewModelScope.launch {
			val sections = databaseRepository.getSections()
			if (sections.isEmpty()) {
				databaseRepository.save(Section(sectionID.value, name = "", locations = listOf()))
				databaseRepository.save(sectionID.value, Location(sectionID = sectionID.value, name = "", imageInfo = listOf()))
				section.value = databaseRepository.getSections().first()

			} else {
				section.value = sections.first()
				sectionName.value = section.value?.name.orEmpty()
				locationName.value = section.value?.locations?.firstOrNull()?.name.orEmpty()
			}
			adapter.refresh()
		}

		sectionName.observe(viewModelScope) {
			//_itemList.clear()
			_isLoading.value = true
			val section = section.value
			if (section != null) {
				section.name = it
				databaseRepository.renameSection(section)
			}
			adapter.refresh()
			_isLoading.value = false
		}

		locationName.observe(viewModelScope){
			_isLoading.value = true
			val location = section.value?.locations?.firstOrNull()
			if (location != null){
				location.name = it
				databaseRepository.renameLocation(location)
			}
			_isLoading.value = false
		}
		editMode.observe(viewModelScope){
			if (!it) selectImage.clear()
		}
	}

	fun addImages(streams: List<InputStream>) {
		viewModelScope.launch(Dispatchers.IO) {
			val locationID = section.value?.locations?.firstOrNull()?.id ?: return@launch
			_isLoading.value = true
			streams.forEach {
				val uuid = UUID.randomUUID()
				val bytes = it.readBytes()
				fileProvider.save(bytes, uuid)
				networkRepository.saveImageInfo(uuid, bytes)
				databaseRepository.save(locationID, ImageInfo(uuid = uuid, location = locationName.value))
				val info = databaseRepository.getImageInfo(locationID)
				section.value?.locations?.firstOrNull { loc -> loc.id == locationID }?.imageInfo = info
			}
			adapter.refresh()
			_isLoading.value = false
		}
	}

	fun delete() {
		viewModelScope.launch {
			_isLoading.value = true
			selectImage.filter { it.value }.forEach { (t, _) ->
				val uuid = t.uuid
				fileProvider.delete(uuid)
				networkRepository.deleteImageInfo(uuid)
				databaseRepository.delete(t)
			}
			section.value?.let {
				it.locations = databaseRepository.getLocations(it.id)
			}
			selectImage.clear()
			adapter.refresh()
			editMode.value = false
			_isLoading.value = false
		}
	}

}