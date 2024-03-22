package com.uogames.locationtest.ui.fragment.bottom

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uogames.locationtest.ui.custom.CardImage
import com.uogames.locationtest.utlis.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImagePickerAdapter(
	private val callSize: () -> Int,
	private val callItem: (position: Int) -> BottomViewModel.ImageSelect?
) : RecyclerView.Adapter<ImagePickerAdapter.Holder>() {

	private val recyclerScope = CoroutineScope(Dispatchers.Main)
	private val refreshFlow = MutableStateFlow(0)

	inner class Holder(private val view: CardImage) : RecyclerView.ViewHolder(view) {

		private var job: Job? = null

		fun show(position: Int) {
			Log.e("TAG", "show: $position", )
			job = refreshFlow.observe(recyclerScope) {
				val item = callItem(position) ?: return@observe
				view.selectPhotoView.visibility = View.VISIBLE
				view.check(item.select)
			}
		}

		fun onDestroy() {
			job?.cancel()
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(CardImage(parent.context))
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

	fun refresh() {
		refreshFlow.value++
		if (itemCount < 2) recyclerScope.launch { notifyDataSetChanged() }
	}


}