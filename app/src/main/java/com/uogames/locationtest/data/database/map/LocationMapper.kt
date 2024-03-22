package com.uogames.locationtest.data.database.map

import com.uogames.database.entity.LocationEntity
import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.domain.entity.Location

object LocationMapper {

	fun Location.toEntity(sectionID: Int) = LocationEntity(
		id = id,
		uuid = uuid,
		sectionID = sectionID,
		name = name
	)

	fun LocationEntity.toDTO(images: List<ImageInfo>) = Location(
		id = id,
		uuid = uuid,
		sectionID = sectionID,
		name = name,
		imageInfo = images
	)


}