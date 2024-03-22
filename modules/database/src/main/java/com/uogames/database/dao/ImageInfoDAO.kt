package com.uogames.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uogames.database.entity.ImageInfoEntity
import java.util.UUID

typealias LocationName = String

@Dao
interface ImageInfoDAO {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(entity: ImageInfoEntity): Long

	@Update
	suspend fun update(entity: ImageInfoEntity)

	@Delete
	suspend fun delete(entity: ImageInfoEntity): Int

	@Query("SELECT * FROM image_info WHERE id = :id")
	suspend fun getByID(id: Int): ImageInfoEntity?

	@Query("SELECT * FROM image_info WHERE uuid = :uuid")
	suspend fun getByUUID(uuid: UUID): ImageInfoEntity?

	@Query(
		"SELECT ii.* FROM image_info AS ii " +
				"LEFT JOIN location_table AS lt " +
				"ON  ii.location_id == lt.id " +
				"WHERE lt.name = :location LIMIT :limit OFFSET:offset"
	)
	suspend fun getList(location: String, limit: Int = Int.MAX_VALUE, offset: Long = 0): List<ImageInfoEntity>

	@Query("SELECT * FROM image_info WHERE location_id = :locationID LIMIT :limit OFFSET:offset")
	suspend fun getListByLocationID(locationID: Int, limit: Int = Int.MAX_VALUE, offset: Long = 0): List<ImageInfoEntity>


}