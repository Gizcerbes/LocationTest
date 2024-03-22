package com.uogames.locationtest.ui.fragment.location

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.ui.custom.CardImage
import com.uogames.locationtest.utlis.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

typealias ImageUri = String

class ImagePickerAdapter(
	private val callSize: () -> Int,
	private val callItem: (position: Int) -> ImageInfo?,
	private val selectItem: (ImageInfo, Boolean) -> Unit,
	private val isSelected: (ImageInfo) -> Boolean,
	private val callImageLink: suspend (ImageInfo) -> Uri?,
	private val longClick: () -> Unit,
	private val isEditMode: StateFlow<Boolean>
) : RecyclerView.Adapter<ImagePickerAdapter.Holder>() {

	private val recyclerScope = CoroutineScope(Dispatchers.Main)
	private val refreshFlow = MutableStateFlow(0)
	private var attached = 0

	private val _selectImage = MutableSharedFlow<ImageUri>()
	val selectImage = _selectImage.asSharedFlow()

	inner class Holder(private val view: CardImage) : RecyclerView.ViewHolder(view) {

		private var job: Job? = null

		fun show(position: Int) {
			job = recyclerScope.launch {
				refreshFlow.observe(this) {
					val item = callItem(adapterPosition) ?: return@observe
					val isSelected = isSelected(item)
					val imageUri = callImageLink(item)
					view.checkForDelete(isSelected)
					Glide.with(view).load(imageUri).into(view.image)
					view.deletePhotoView.setOnClickListener {
						val select = isSelected(item)
						selectItem(item, !select)
						view.checkForDelete(!select)
					}
					view.setOnLongImageClickListener {
						longClick()
						true
					}
					view.setOnImageClickListener {
						recyclerScope.launch {
							if (isEditMode.value) view.deletePhotoView.performClick()
							else _selectImage.emit(imageUri.toString())
						}
					}
				}
				isEditMode.observe(this) {
					view.deletePhotoView.visibility = if (it) View.VISIBLE else View.GONE
				}
			}
		}

		fun onDestroy() {
			job?.cancel()
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(CardImage(parent.context).apply { })
	}

	override fun getItemCount(): Int {
		return callSize()
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.show(position)
	}

	override fun onViewRecycled(holder: Holder) {
		holder.onDestroy()
	}


	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		attached++
		super.onAttachedToRecyclerView(recyclerView)
	}

	override fun onViewDetachedFromWindow(holder: Holder) {
		attached--
		super.onViewDetachedFromWindow(holder)
	}


	fun refresh() {
		refreshFlow.value++
		if (attached < 2) recyclerScope.launch { notifyDataSetChanged() }
	}


}