package com.uogames.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uogames.database.entity.SectionEntity
import java.util.UUID

@Dao
interface SectionDAO {


	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(entity: SectionEntity): Long

	@Update
	suspend fun update(entity: SectionEntity): Int

	@Delete
	suspend fun delete(entity: SectionEntity): Int

	@Query("SELECT * FROM section_table WHERE id = :id")
	suspend fun getByID(id: Int): SectionEntity?

	@Query("SELECT * FROM section_table WHERE uuid = :uuid")
	suspend fun getByUUID(uuid: UUID): SectionEntity?

	@Query("SELECT * FROM section_table WHERE name = :name LIMIT :limit OFFSET:offset")
	suspend fun getListByName(name: String, limit: Int = Int.MAX_VALUE, offset: Long = 0): List<SectionEntity>

	@Query("SELECT * FROM section_table")
	suspend fun gelList(): List<SectionEntity>

	@Query("SELECT COUNT(id) FROM section_table")
	suspend fun count(): Long

}