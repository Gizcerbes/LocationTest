package com.uogames.locationtest.data.firestore

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.uogames.locationtest.domain.entity.ImageInfo
import com.uogames.locationtest.domain.interfaces.NetworkRepository
import kotlinx.coroutines.tasks.await
import java.util.UUID

class CloudStorageProvider(
	private val storage: FirebaseStorage
) : NetworkRepository {

	private val folder = "images/"

	override suspend fun saveImageInfo(uuid: UUID, bytes: ByteArray): Boolean {
		return try {
			val imageRef = storage.reference.child("$folder${uuid}")
			return imageRef.putBytes(bytes).await().task.isSuccessful
		} catch (e: Throwable) {
			false
		}
	}

	override suspend fun getImageInfoByUUID(uuid: UUID): ByteArray {
		return try {
			val imageRef = storage.reference.child("$folder${uuid}")
			imageRef.getBytes(10000L).await()
		} catch (e: Throwable) {
			ByteArray(0)
		}
	}

	override suspend fun deleteImageInfo(uuid: UUID): Boolean {
		return try {
			val imageRef = storage.reference.child("$folder${uuid}")
			imageRef.delete()
			true
		} catch (e: Throwable){
			false
		}
	}

	override suspend fun getImageUri(uuid: UUID): Uri? {
		return try {
			val imageRef = storage.reference.child("$folder${uuid}")
			imageRef.downloadUrl.await()
		} catch (e: Throwable){
			null
		}

	}
}