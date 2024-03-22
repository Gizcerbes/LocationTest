package com.uogames.database

import android.content.Context
import androidx.room.Room
import com.uogames.database.provider.ImageInfoProvider
import com.uogames.database.provider.LocationProvider
import com.uogames.database.provider.SectionProvider

class AppProvider private constructor(private val database: AppDatabase) {


	companion object {

		private val MAP_INSTANCE = HashMap<DatabaseName, AppProvider>()

		fun get(context: Context, databaseName: String): AppProvider {
			if (MAP_INSTANCE[databaseName] == null) synchronized(this) {
				if (MAP_INSTANCE[databaseName] == null) MAP_INSTANCE[databaseName] = AppProvider(Room
					.databaseBuilder(context, AppDatabase::class.java, databaseName)
					.build())
			}
			return MAP_INSTANCE[databaseName] as AppProvider
		}

	}

	fun getImageProvider() = ImageInfoProvider(database.imageInfoDAO())

	fun getLocationProvider() = LocationProvider(database.locationDAO())

	fun getSectionProvider() = SectionProvider(database.sectionDAO())


}