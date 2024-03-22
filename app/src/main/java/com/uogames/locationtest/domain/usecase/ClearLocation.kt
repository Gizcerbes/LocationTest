package com.uogames.locationtest.domain.usecase

interface ClearLocation {


	suspend fun clearLocation(locationID: Int): Boolean


}