package com.uogames.database.provider

import com.uogames.database.dao.ImageInfoDAO
import com.uogames.database.entity.ImageInfoEntity
import java.util.UUID

class ImageInfoProvider(private val dao: ImageInfoDAO) {

	suspend fun save(entity: ImageInfoEntity) = dao.save(entity)

	suspend fun delete(entity: ImageInfoEntity) = dao.delete(entity)

	suspend fun getByID(id: Int) = dao.getByID(id)

	suspend fun getByUUID(uuid: UUID) = dao.getByUUID(uuid)

	suspend fun getList(location: String, limit: Int = Int.MAX_VALUE, offset: Long = 0) = dao.getList(location, limit, offset)

	suspend fun getListByLocationID(locationID: Int, limit: Int = Int.MAX_VALUE, offset: Long = 0) =
		dao.getListByLocationID(locationID, limit, offset)
}