package co.veradebv.catalogly.data.repository

import co.veradebv.catalogly.data.local.db.CartDao
import co.veradebv.catalogly.data.local.entity.CartEntity
import co.veradebv.catalogly.domain.model.CartItem
import co.veradebv.catalogly.domain.repository.CartRepository
import co.veradebv.catalogly.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override fun getCartItems(): Flow<Resource<List<CartItem>>> =
        cartDao.getCartItems()
            .map { entities ->
                Resource.Success(entities.map { it.toDomain() }) as Resource<List<CartItem>>
            }
            .catch { e ->
                emit(Resource.Error(e.message ?: "Failed to load cart"))
            }

    override suspend fun addToCart(cartItem: CartItem) {
        val existing = cartDao.getCartItem(cartItem.productId)
        if (existing != null) {
            // Item already in cart — increment quantity
            cartDao.updateCartItem(existing.copy(quantity = existing.quantity + 1))
        } else {
            // New item — insert fresh
            cartDao.insertCartItem(cartItem.toEntity())
        }
    }

    override suspend fun removeFromCart(productId: Int) {
        cartDao.removeCartItem(productId)
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }

    override fun getCartItemCount(): Flow<Int> =
        cartDao.getCartItemCount()
}

// Local mapper extensions for CartEntity
private fun CartEntity.toDomain(): CartItem = CartItem(
    productId = productId,
    title = title,
    price = price,
    image = image,
    quantity = quantity
)

private fun CartItem.toEntity(): CartEntity = CartEntity(
    productId = productId,
    title = title,
    price = price,
    image = image,
    quantity = quantity
)