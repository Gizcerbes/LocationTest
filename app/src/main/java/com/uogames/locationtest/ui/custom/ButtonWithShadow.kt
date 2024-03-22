package com.uogames.locationtest.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.uogames.locationtest.databinding.ButtonWithShadowBinding

class ButtonWithShadow (context: Context, attr: AttributeSet? = null) : RelativeLayout(context, attr) {


	private val bind = ButtonWithShadowBinding.inflate(LayoutInflater.from(context), this, false)

	val button get() =  bind.btnDelete

	init {
		addView(bind.root)


	}

}