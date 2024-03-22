package com.uogames.locationtest.domain.entity

import java.util.UUID

data class Location(
	val id: Int = 0,
	val uuid: UUID = UUID.randomUUID(),
	val sectionID: Int,
	var name: String,
	var imageInfo: List<ImageInfo>
)