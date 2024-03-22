package com.uogames.locationtest.domain.interfaces

import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.domain.usecase.GetImageUri
import java.util.UUID

interface NetworkRepository : GetImageUri {

	suspend fun saveImageInfo(uuid: UUID, bytes: ByteArray): Boolean

	suspend fun getImageInfoByUUID(uuid: UUID): ByteArray


	suspend fun deleteImageInfo(uuid: UUID): Boolean

}