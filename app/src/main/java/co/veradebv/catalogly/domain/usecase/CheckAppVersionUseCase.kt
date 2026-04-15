package co.veradebv.catalogly.domain.usecase

import co.veradebv.catalogly.domain.repository.UpdateRepository
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val repository: UpdateRepository
) {
    suspend operator fun invoke(): Boolean =
        repository.checkForUpdate()
}