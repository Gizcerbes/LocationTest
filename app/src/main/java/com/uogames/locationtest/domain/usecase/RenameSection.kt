package com.uogames.locationtest.domain.usecase

import com.uogames.locationtest.domain.entity.Section

interface RenameSection {

	suspend fun renameSection(section: Section): Boolean


}