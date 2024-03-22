package com.uogames.locationtest.data.database

import com.uogames.database.AppProvider
import com.uogames.locationtest.data.database.map.ImageInfoMapper.toDTO
import com.uogames.locationtest.data.database.map.ImageInfoMapper.toEntity
import com.uogames.locationtest.data.database.map.LocationMapper.toDTO
import com.uogames.locationtest.data.database.map.LocationMapper.toEntity
import com.uogames.locationtest.data.database.map.SectionMapper.toDTO
import com.uogames.locationtest.data.database.map.SectionMapper.toEntity
import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.domain.entity.Location
import com.uogames.locationtest.domain.entity.Section
import com.uogames.locationtest.domain.interfaces.DatabaseRepository
import javax.inject.Inject

class DatabaseProvider @Inject constructor(
	private val dao: AppProvider
) : DatabaseRepository {


	override suspend fun save(section: Section): Boolean {
		return try {
			val id = dao.getSectionProvider().save(section.toEntity())
			section.locations.forEach { save(id.toInt(), it) }
			true
		} catch (e: Exception) {
			false
		}
	}

	override suspend fun save(sectionID: Int, location: Location): Boolean {
		return try {
			val id = dao.getLocationProvider().save(location.toEntity(sectionID))
			location.imageInfo.forEach { save(id.toInt(), it) }
			true
		} catch (e: Exception) {
			false
		}
	}

	override suspend fun save(locationID: Int, imageInfo: ImageInfo): Boolean {
		return try {
			dao.getImageProvider().save(imageInfo.toEntity(locationID))
			true
		} catch (e: Exception) {
			false
		}
	}

	override suspend fun delete(section: Section): Boolean {
		return dao.getSectionProvider().delete(section.toEntity()) > 0
	}

	override suspend fun delete(location: Location): Boolean {
		val entity = dao.getLocationProvider().getByID(location.id)
		return entity?.let { dao.getLocationProvider().delete(it) > 0 } ?: false
	}

	override suspend fun delete(imageInfo: ImageInfo): Boolean {
		val entity = dao.getImageProvider().getByID(imageInfo.id)
		return entity?.let { dao.getImageProvider().delete(it) > 0 } ?: false
	}

	override suspend fun getSections(): List<Section> {
		return dao.getSectionProvider().gelList().map { it.toDTO(getLocations(it.id)) }
	}

	override suspend fun getLocations(sectionID: Int): List<Location> {
		return dao.getLocationProvider().getListByID(sectionID).map { it.toDTO(getImageInfo(it.id)) }
	}

	override suspend fun getImageInfo(locationID: Int): List<ImageInfo> {
		val location = dao.getLocationProvider().getByID(locationID) ?: return listOf()
		return dao.getImageProvider().getListByLocationID(locationID).map { it.toDTO(location.name) }
	}

	override suspend fun renameSection(section: Section): Boolean {
		return dao.getSectionProvider().update(section.toEntity()) > 0
	}

	override suspend fun renameLocation(location: Location): Boolean {
		val l = dao.getLocationProvider().getByID(location.id) ?: return false
		return dao.getLocationProvider().update(location.toEntity(l.sectionID)) > 0
	}




}