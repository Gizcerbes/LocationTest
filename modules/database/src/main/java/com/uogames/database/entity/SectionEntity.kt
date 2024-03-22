package com.uogames.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("section_table")
data class SectionEntity(
	@ColumnInfo("id")
	@PrimaryKey(autoGenerate = true)
	val id: Int,
	@ColumnInfo("uuid")
	val uuid: UUID,
	@ColumnInfo("name")
	val name: String
)