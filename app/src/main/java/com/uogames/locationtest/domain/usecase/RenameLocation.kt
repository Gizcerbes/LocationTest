package com.uogames.locationtest.domain.usecase

import com.uogames.locationtest.domain.entity.Location

interface RenameLocation {

	suspend fun renameLocation(location: Location): Boolean

}