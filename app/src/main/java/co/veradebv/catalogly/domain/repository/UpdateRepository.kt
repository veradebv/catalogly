package co.veradebv.catalogly.domain.repository

interface UpdateRepository {
    suspend fun checkForUpdate(): Boolean
}