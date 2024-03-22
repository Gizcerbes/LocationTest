package com.uogames.locationtest.data.file

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.uogames.locationtest.domain.interfaces.ImageRepository
import com.uogames.locationtest.domain.usecase.GetImageUri
import java.lang.ref.WeakReference
import java.util.UUID

class FileProvider(
	private val context: WeakReference<Context>
) : ImageRepository, AutoCloseable {


	override suspend fun save(byteArray: ByteArray, uuid: UUID) {
		context.get()?.openFileOutput(buildPath(uuid), Context.MODE_PRIVATE)?.use {
			it.write(byteArray)
			it.flush()
		}
	}

	override suspend fun delete(uuid: UUID): Boolean {
		return if (exists(uuid)) {
			context.get()?.deleteFile(buildPath(uuid)) ?: false
		} else false
	}

	override suspend fun get(uuid: UUID): ByteArray? {
		return context.get()?.openFileInput(buildPath(uuid))?.readBytes()
	}

	override suspend fun exists(uuid: UUID): Boolean {
		return context.get()?.getFileStreamPath(buildPath(uuid))?.exists() ?: false
	}

	override suspend fun count(): Int {
		return context.get()?.filesDir?.listFiles()?.size ?: 0
	}

	override suspend fun clear(): Boolean {
		return try {
			context.get()?.filesDir?.listFiles()?.forEach { it.delete() }
			true
		} catch (e : Exception){
			false
		}
	}

	override fun close() {
		context.clear()
	}

	private fun buildPath(uuid: UUID): String {
		return "$uuid".replace("//", "/")
	}

	override suspend fun getImageUri(uuid: UUID): Uri? {
		return context.get()?.getFileStreamPath(buildPath(uuid))?.toUri()
	}


}