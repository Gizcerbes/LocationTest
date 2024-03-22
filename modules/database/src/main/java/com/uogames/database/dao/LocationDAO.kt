package com.uogames.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uogames.database.entity.LocationEntity
import java.util.UUID

@Dao
interface LocationDAO {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(entity: LocationEntity): Long

	@Update
	suspend fun update(entity: LocationEntity): Int

	@Delete
	suspend fun delete(entity: LocationEntity): Int

	@Query("SELECT * FROM location_table WHERE id = :id")
	suspend fun getByID(id: Int): LocationEntity?

	@Query("SELECT * FROM location_table WHERE uuid = :uuid")
	suspend fun getByUUID(uuid: UUID): LocationEntity?


	@Query(
		"SELECT lt.* FROM location_table AS lt " +
				"JOIN section_table AS st " +
				"ON lt.section_id = st.id " +
				"WHERE st.name = :section LIMIT :limit OFFSET:offset"
	)
	suspend fun getList(section: String, limit: Int = Int.MAX_VALUE, offset: Long = 0): List<LocationEntity>

	@Query("SELECT * FROM location_table WHERE section_id = :sectionID LIMIT :limit OFFSET:offset")
	suspend fun getListByID(sectionID: Int, limit: Int = Int.MAX_VALUE, offset: Long = 0): List<LocationEntity>

}