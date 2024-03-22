package com.uogames.locationtest.data.database.map

import com.uogames.database.entity.ImageInfoEntity
import com.uogames.locationtest.domain.entity.ImageInfo

object ImageInfoMapper {

	fun ImageInfoEntity.toDTO(
		locationName: String
	): ImageInfo = ImageInfo(
		id = id,
		uuid = uuid,
		location = locationName
	)

	fun ImageInfo.toEntity(
		locationID: Int
	): ImageInfoEntity = ImageInfoEntity(
		id = id,
		uuid = uuid,
		locationID = locationID
	)

}