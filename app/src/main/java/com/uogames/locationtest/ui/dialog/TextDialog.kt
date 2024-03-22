package com.uogames.locationtest.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.uogames.locationtest.R
import com.uogames.locationtest.databinding.CardTextInputBinding

object TextDialog {


	fun show(
		context: Context,
		@StringRes title: Int,
		startText: String,
		result: (String) -> Unit
	) {

		val container = CardTextInputBinding.inflate(LayoutInflater.from(context))
		container.til.editText?.setText(startText)
		container.til.editText?.setSelection(startText.length)

		MaterialAlertDialogBuilder(context)
			.setTitle(title)
			.setView(container.root)
			.setPositiveButton(R.string.ok) { _, _ -> result(container.til.editText?.text?.toString().orEmpty()) }
			.setNegativeButton(R.string.close) { _, _ -> }
			.show()
	}


}