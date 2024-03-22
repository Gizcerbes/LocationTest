plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("kotlin-kapt")
	id("com.google.dagger.hilt.android")
	id("com.google.gms.google-services")
}

android {
	namespace = "com.uogames.locationtest"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.uogames.locationtest"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		buildConfigField("String", "FIREBASE_STORAGE", Secrets.FIREBASE_STORAGE)

	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		buildConfig = true
		viewBinding = true
	}
}

tasks.register("version") {
	println(android.defaultConfig.versionName)
}

dependencies {

	implementation(project(":modules:database"))

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.androidx.navigation.fragment.ktx)
	implementation(libs.androidx.navigation.ui.ktx)

	implementation(libs.androidx.core.splashscreen)
	implementation(libs.picasso)
	implementation(libs.glide)

	implementation(libs.hilt.android)



	implementation(libs.google.firebase.storage)

	kapt(libs.hilt.compiler)



	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}