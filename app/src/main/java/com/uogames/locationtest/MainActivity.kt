package com.uogames.locationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.uogames.locationtest.utlis.Permission
import dagger.hilt.android.AndroidEntryPoint

interface SplashEnd {

	fun splashEnd()

}

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SplashEnd {

	private var splash = false

	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen().apply { setKeepOnScreenCondition{ splash } }
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)


	}

	override fun splashEnd() {
		splash = false
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		runCatching {
			Permission.entries[requestCode].onRequestPermissionResult(grantResults)
		}
	}
}