package com.uogames.kirmash.utils

import com.squareup.picasso.Callback
import java.lang.Exception

class PicassoPositiveCallback(private val block: () -> Unit) : Callback {
	override fun onSuccess() = block()


	override fun onError(e: Exception?) {}

}

class PicassoNegativeCallback(private val block: (Exception?) -> Unit) : Callback {
	override fun onSuccess() {}

	override fun onError(e: Exception?)  = block(e)

}