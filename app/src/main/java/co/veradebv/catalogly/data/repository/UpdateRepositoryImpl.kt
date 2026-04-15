package co.veradebv.catalogly.data.repository

import co.veradebv.catalogly.domain.repository.UpdateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateRepositoryImpl @Inject constructor() : UpdateRepository {

    // Placeholder — we'll fill this in during Step 8 (In-App Updates)
    // The actual AppUpdateManager requires an Activity reference
    // which we'll handle properly using ActivityResultLauncher

    override suspend fun checkForUpdate(): Boolean = false
}