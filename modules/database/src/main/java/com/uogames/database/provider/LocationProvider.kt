package com.uogames.database.provider

import com.uogames.database.dao.LocationDAO
import com.uogames.database.entity.LocationEntity
import java.util.UUID

class LocationProvider(private val dao: LocationDAO) {

	suspend fun save(entity: LocationEntity) = dao.save(entity)

	suspend fun update(entity: LocationEntity) = dao.update(entity)

	suspend fun delete(entity: LocationEntity) = dao.delete(entity)

	suspend fun getByID(id: Int) = dao.getByID(id)

	suspend fun getByUUID(uuid: UUID) = dao.getByUUID(uuid)

	suspend fun getList(section: String, limit: Int = Int.MAX_VALUE, offset: Long = 0) =
		dao.getList(section, limit, offset)

	suspend fun getListByID(sectionID: Int, limit: Int = Int.MAX_VALUE, offset: Long = 0) =
		dao.getListByID(sectionID, limit, offset)

}