package com.uogames.locationtest.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.uogames.locationtest.databinding.CardImageBinding

class CardImage (context: Context, attr: AttributeSet? = null) : RelativeLayout(context, attr) {

	private val bind = CardImageBinding.inflate(LayoutInflater.from(context), this, false)

	val image = bind.ivPhoto

	val deletePhotoView: View = bind.mcvDeletePhoto
	val selectPhotoView: View = bind.mcvSelectPhoto

	val isDeleteChecked get() =  deletePhotoView.visibility == VISIBLE
	val isChecked get() = selectPhotoView.visibility == VISIBLE

	init {
		addView(bind.root)
	}

	fun checkForDelete(b: Boolean){
		bind.deletePhotoSelect.visibility = if (b) VISIBLE else GONE
	}

	fun check(b: Boolean){
		bind.checkPhotoSelect.visibility = if (b) VISIBLE else GONE
	}

	fun setOnImageClickListener(l: OnClickListener?){
		bind.mcvContent.setOnClickListener(l)
	}

	fun setOnLongImageClickListener(l: OnLongClickListener?){
		bind.mcvContent.setOnLongClickListener(l)
	}



}