package com.uogames.locationtest.domain.interfaces

import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.domain.entity.Location
import com.uogames.locationtest.domain.entity.Section
import com.uogames.locationtest.domain.usecase.ClearLocation
import com.uogames.locationtest.domain.usecase.RenameLocation
import com.uogames.locationtest.domain.usecase.RenameSection
import java.util.UUID

interface DatabaseRepository: RenameSection, RenameLocation {

	suspend fun save(section: Section): Boolean

	suspend fun save(sectionID: Int, location: Location): Boolean

	suspend fun save(locationID: Int, imageInfo: ImageInfo): Boolean

	suspend fun delete(section: Section): Boolean

	suspend fun delete(location: Location): Boolean

	suspend fun delete(imageInfo: ImageInfo): Boolean

	suspend fun getSections(): List<Section>

	suspend fun getLocations(sectionID: Int): List<Location>

	suspend fun getImageInfo(locationID: Int): List<ImageInfo>


}