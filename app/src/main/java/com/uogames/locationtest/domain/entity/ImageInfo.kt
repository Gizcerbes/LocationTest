package com.uogames.locationtest.domain.entity

import java.util.UUID

data class ImageInfo(
	val id: Int = 0,
	val uuid: UUID = UUID.randomUUID(),
	val location: String
)
