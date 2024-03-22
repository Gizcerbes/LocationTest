package com.uogames.locationtest.domain.interfaces

import com.uogames.locationtest.domain.usecase.GetImageUri
import java.util.UUID

interface ImageRepository : GetImageUri{

	suspend fun save(byteArray: ByteArray, uuid: UUID)

	suspend fun delete(uuid: UUID): Boolean

	suspend fun get(uuid: UUID) : ByteArray?

	suspend fun exists(uuid: UUID): Boolean

	suspend fun count(): Int

	suspend fun clear(): Boolean

}