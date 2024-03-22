package com.uogames.database.provider

import com.uogames.database.dao.SectionDAO
import com.uogames.database.entity.SectionEntity
import java.util.UUID

class SectionProvider(private val dao: SectionDAO) {


	suspend fun save(entity: SectionEntity) = dao.save(entity)

	suspend fun update(entity: SectionEntity) = dao.update(entity)

	suspend fun delete(entity: SectionEntity) = dao.delete(entity)

	suspend fun getByID(id: Int) = dao.getByID(id)

	suspend fun getByUUID(uuid: UUID) = dao.getByUUID(uuid)

	suspend fun getListByName(name: String, limit: Int = Int.MAX_VALUE, offset: Long = 0)=
		dao.getListByName(name, limit, offset)

	suspend fun gelList() = dao.gelList()

	suspend fun count() = dao.count()

}