package co.veradebv.catalogly.domain.usecase

import co.veradebv.catalogly.domain.model.CartItem
import co.veradebv.catalogly.domain.model.Product
import co.veradebv.catalogly.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(product: Product) {
    val cartItem = CartItem(
        productId = product.id,
        title = product.title,
        price = product.price,
        image = product.image,
        quantity = 1
    )
        repository.addToCart(cartItem)
    }
}