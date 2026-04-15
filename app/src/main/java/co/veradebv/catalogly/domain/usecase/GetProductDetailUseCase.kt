package co.veradebv.catalogly.domain.usecase

import co.veradebv.catalogly.domain.model.Product
import co.veradebv.catalogly.domain.repository.ProductRepository
import co.veradebv.catalogly.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Product>> =
        repository.getProductById(id)
}