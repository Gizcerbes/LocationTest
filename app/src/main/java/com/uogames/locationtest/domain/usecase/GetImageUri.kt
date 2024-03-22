package com.uogames.locationtest.domain.usecase

import android.net.Uri
import java.util.UUID

interface GetImageUri {


	suspend fun getImageUri(uuid: UUID): Uri?


}