package com.uogames.locationtest.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.uogames.locationtest.databinding.CardLocationBinding

class CardLocation(context: Context, attr: AttributeSet? = null) : RelativeLayout(context, attr) {


	private val bind = CardLocationBinding.inflate(LayoutInflater.from(context), this, false)

	val locationName get() =  bind.tvLocationName

	val locationImages get() = bind.rvLocationImages

	val buttonAdd get() = bind.mcvAddPhoto

	val buttonDelete get() = bind.btnDelete.button

	init {
		addView(bind.root)
	}


}