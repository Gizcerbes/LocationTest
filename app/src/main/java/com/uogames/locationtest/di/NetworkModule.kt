package com.uogames.locationtest.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.squareup.picasso.Picasso
import com.uogames.locationtest.data.firestore.CloudStorageProvider
import com.uogames.locationtest.domain.interfaces.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


	@Singleton
	@Provides
	fun provideNetworkRepository(): NetworkRepository = CloudStorageProvider(Firebase.storage("gs://localetest-15a31.appspot.com"))


}