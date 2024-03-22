package com.uogames.locationtest.di

import android.content.Context
import com.uogames.database.AppDatabase
import com.uogames.database.AppProvider
import com.uogames.locationtest.data.database.DatabaseProvider
import com.uogames.locationtest.data.file.FileProvider
import com.uogames.locationtest.domain.interfaces.DatabaseRepository
import com.uogames.locationtest.domain.interfaces.ImageRepository
import com.uogames.locationtest.domain.usecase.GetImageUri
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.ref.WeakReference
import javax.inject.Singleton

const val DATABASE_NAME = "IMAGE_INFO_DATABASE"

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

	@Singleton
	@Provides
	fun provideImageRepository(
		@ApplicationContext context: Context
	): ImageRepository = FileProvider(WeakReference(context))

	@Singleton
	@Provides
	fun provideDatabase(
		@ApplicationContext context: Context
	): DatabaseRepository {
		val database = AppProvider.get(context, DATABASE_NAME)
		return DatabaseProvider(database)
	}
}