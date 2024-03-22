package com.uogames.locationtest.data.database.map

import com.uogames.database.entity.SectionEntity
import com.uogames.locationtest.domain.entity.Location
import com.uogames.locationtest.domain.entity.Section

object SectionMapper {

	fun Section.toEntity() = SectionEntity(
		id = id,
		uuid = uuid,
		name = name
	)

	fun SectionEntity.toDTO(locationsList: List<Location>) = Section(
		id = id,
		uuid = uuid,
		name = name,
		locations = locationsList
	)


}