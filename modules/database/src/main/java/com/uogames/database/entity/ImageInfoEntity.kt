package com.uogames.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
	tableName = "image_info",
	indices = [
		Index("uuid", unique = true)
	],
	foreignKeys = [
		ForeignKey(
			entity = LocationEntity::class,
			parentColumns = ["id"],
			childColumns = ["location_id"],
			onDelete = ForeignKey.CASCADE
		)
	]
)
data class ImageInfoEntity(
	@ColumnInfo("id")
	@PrimaryKey(autoGenerate = true)
	val id: Int,
	@ColumnInfo("uuid")
	val uuid: UUID,
	@ColumnInfo("location_id")
	val locationID: Int
)