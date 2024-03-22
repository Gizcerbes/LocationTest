package com.uogames.locationtest.ui.fragment.bottom

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BottomViewModel @Inject constructor() : ViewModel() {

	data class ImageSelect(var select: Boolean, val uri: Uri)

	private val _imageList = ArrayList<ImageSelect>()

	val selectImageAdapter = ImagePickerAdapter(
		callSize = { runCatching { _imageList.size }.getOrNull() ?: 0 },
		callItem = { runCatching { _imageList[it] }.getOrNull() }
	)

	val showImagePicker = MutableStateFlow<Boolean>(false)

	fun addToImagePickerList(uri: Uri) {
		_imageList.add(ImageSelect(false, uri))
	}

	fun cleanImagePicker() {
		_imageList.clear()
		selectImageAdapter.refresh()
	}
}