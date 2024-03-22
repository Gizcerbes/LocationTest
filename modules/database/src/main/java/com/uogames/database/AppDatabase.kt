package com.uogames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uogames.database.dao.ImageInfoDAO
import com.uogames.database.dao.LocationDAO
import com.uogames.database.dao.SectionDAO
import com.uogames.database.entity.ImageInfoEntity
import com.uogames.database.entity.LocationEntity
import com.uogames.database.entity.SectionEntity

typealias DatabaseName = String

@Database(
	version = 1,
	entities = [
		SectionEntity::class,
		LocationEntity::class,
		ImageInfoEntity::class
	]
)
abstract class AppDatabase: RoomDatabase() {


	companion object {

		private val MAP_INSTANCE = HashMap<DatabaseName, AppDatabase>()

		fun get(context: Context, databaseName: String): AppDatabase {
			if (MAP_INSTANCE[databaseName] == null) synchronized(this) {
				if (MAP_INSTANCE[databaseName] == null) MAP_INSTANCE[databaseName] = Room
					.databaseBuilder(context, AppDatabase::class.java, databaseName)
					.build()
			}
			return MAP_INSTANCE[databaseName] as AppDatabase
		}

	}

	abstract fun imageInfoDAO(): ImageInfoDAO

	abstract fun locationDAO() : LocationDAO

	abstract fun sectionDAO(): SectionDAO


}