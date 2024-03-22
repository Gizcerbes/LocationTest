package com.uogames.locationtest.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.uogames.locationtest.databinding.CardSectionBinding

class CardSection(context: Context, attr: AttributeSet? = null) : RelativeLayout(context, attr) {

	private val bind = CardSectionBinding.inflate(LayoutInflater.from(context), this, false)

	val button get() = bind.mcvStreets

	var text: CharSequence?
		get() = bind.sectionText.text
		set(value) {
			bind.sectionText.text = value
		}


	init {
		addView(bind.root)
	}

}