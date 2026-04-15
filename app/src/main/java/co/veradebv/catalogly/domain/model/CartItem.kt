package co.veradebv.catalogly.domain.model

data class CartItem(
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int = 1
) {
    // Total price for this line item
    val subtotal: Double
        get() = price * quantity

    val formattedSubtotal: String
        get() = "$${String.format("%.2f", subtotal)}"

    val formattedPrice: String
        get() = "$${String.format("%.2f", price)}"

}