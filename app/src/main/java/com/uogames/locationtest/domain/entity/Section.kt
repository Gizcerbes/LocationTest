package com.uogames.locationtest.domain.entity

import java.util.UUID

data class Section(
	val id: Int = 0,
	val uuid: UUID = UUID.randomUUID(),
	var name: String,
	var locations: List<Location>
)