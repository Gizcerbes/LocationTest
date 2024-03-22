package com.uogames.locationtest.utlis

import java.io.File

class FileDirectoryScanner(fileDir: File, val type: List<String>) {

	private val fileUrlList = ArrayList<String>()

	init {
		searching(fileDir)
	}

	private fun searching(fileDir: File) {
		fileDir.listFiles()?.forEach { file ->
			if (file.isDirectory) searching(file)
			else type.forEach {
				if (file.name.endsWith(it, ignoreCase = true)) fileUrlList.add(file.absolutePath)
			}
		}
	}

	fun addFromScanning(fileDir: File) = searching(fileDir)


	fun getFileList() = fileUrlList.toList()


}