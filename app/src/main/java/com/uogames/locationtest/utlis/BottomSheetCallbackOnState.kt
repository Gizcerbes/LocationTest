package com.uogames.locationtest.utlis

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback

class BottomSheetCallbackOnState(val onChanged: (view: View, newState: Int) -> Unit) : BottomSheetCallback() {

	override fun onStateChanged(p0: View, p1: Int) {
		onChanged(p0, p1)
	}

	override fun onSlide(p0: View, p1: Float) {}
}

fun BottomSheetBehavior<View>.addOnStateCallback(onChanged: (view: View, newState: Int) -> Unit) {
	addBottomSheetCallback(BottomSheetCallbackOnState(onChanged))
}
