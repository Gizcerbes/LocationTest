package com.uogames.locationtest.utlis

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.uogames.kirmash.utils.PicassoNegativeCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch


fun <T> Flow<T>.observe(scope: CoroutineScope, collector: FlowCollector<T>) = scope.launch {
	collect(collector)
}

fun Picasso.load(url: String?, target: ImageView?, @DrawableRes placeholder: Int) {
	if (url == null) target?.setImageResource(placeholder)
	else load(url)
		.networkPolicy(NetworkPolicy.OFFLINE)
		.into(target, PicassoNegativeCallback {
			load(url)
				.placeholder(placeholder)
				.into(target, PicassoNegativeCallback {
					target?.setImageResource(placeholder)
				})
		})
}