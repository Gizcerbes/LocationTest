package com.uogames.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
	tableName = "location_table",
	foreignKeys = [
		ForeignKey(
			entity = SectionEntity::class,
			parentColumns = ["id"],
			childColumns = ["section_id"],
			onDelete = ForeignKey.CASCADE
		)
	]
)
data class LocationEntity(
	@ColumnInfo("id")
	@PrimaryKey(autoGenerate = true)
	val id: Int,
	@ColumnInfo("uuid")
	val uuid: UUID,
	@ColumnInfo("section_id")
	val sectionID: Int,
	@ColumnInfo("name")
	val name: String
)