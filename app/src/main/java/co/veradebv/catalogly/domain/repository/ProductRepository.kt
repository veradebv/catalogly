package co.veradebv.catalogly.domain.repository

import co.veradebv.catalogly.domain.model.Product
import co.veradebv.catalogly.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<Resource<List<Product>>>

    fun getProductById(id: Int): Flow<Resource<Product>>

    fun getProductByCategory(category: String): Flow<Resource<List<Product>>>
}