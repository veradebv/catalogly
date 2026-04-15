package co.veradebv.catalogly.data.repository

import co.veradebv.catalogly.data.local.db.ProductDao
import co.veradebv.catalogly.data.mapper.toDomain
import co.veradebv.catalogly.data.mapper.toEntity
import co.veradebv.catalogly.data.remote.api.ProductApiService
import co.veradebv.catalogly.domain.model.Product
import co.veradebv.catalogly.domain.repository.ProductRepository
import co.veradebv.catalogly.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService,
    private val productDao: ProductDao
) : ProductRepository {

    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
        // Step 1 — immediately emit cached data from Room while we fetch fresh data
        emit(Resource.Loading)

        // Step 2 — fetch from API
        try {
            val remoteProducts = apiService.getProducts()

            // Step 3 — save fresh data to Room (cache refresh)
            productDao.insertProducts(remoteProducts.map { it.toEntity() })

        } catch (e: Exception) {
            // Step 4 — if API fails, emit error but don't wipe the cache
            emit(Resource.Error(e.message ?: "Unknown error occurred", e))
        }

        // Step 5 — always read from Room as the single source of truth
        // Room's Flow will keep emitting whenever the table updates
        productDao.getAllProducts()
            .map { entities ->
                Resource.Success(entities.map { it.toDomain() })
            }
            .collect { emit(it) }
    }

    override fun getProductById(id: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading)
        try {
            val remoteProduct = apiService.getProductById(id)
            productDao.insertProducts(listOf(remoteProduct.toEntity()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred", e))
        }
        productDao.getProductById(id)
            .map { entity ->
                if (entity != null) Resource.Success(entity.toDomain())
                else Resource.Error("Product not found")
            }
            .collect { emit(it) }
    }

    override fun getProductsByCategory(category: String): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading)
        try {
            val remoteProducts = apiService.getProductsByCategory(category)
            productDao.insertProducts(remoteProducts.map { it.toEntity() })
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred", e))
        }
        productDao.getProductsByCategory(category)
            .map { entities -> Resource.Success(entities.map { it.toDomain() }) }
            .collect { emit(it) }
    }
}