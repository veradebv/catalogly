package co.veradebv.catalogly.domain.repository

import co.veradebv.catalogly.domain.model.CartItem
import co.veradebv.catalogly.util.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<Resource<List<CartItem>>>

    suspend fun addToCart(cartItem: CartItem)

    suspend fun removeFromCart(productId: Int)

    suspend fun clearCart()

    fun getCartItemCount(): Flow<Int>
}