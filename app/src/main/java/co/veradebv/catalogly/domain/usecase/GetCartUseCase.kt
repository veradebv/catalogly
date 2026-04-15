package co.veradebv.catalogly.domain.usecase

import co.veradebv.catalogly.domain.model.CartItem
import co.veradebv.catalogly.domain.repository.CartRepository
import co.veradebv.catalogly.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: CartRepository

){
    operator fun invoke(): Flow<Resource<List<CartItem>>> =
        repository.getCartItems()

    fun getTotalPrice(): Flow<Double> =
        repository.getCartItems().map { resource ->
            when (resource) {
                is Resource.Success -> resource.data.sumOf { it.subtotal }
                else -> 0.0
        }
    }
}