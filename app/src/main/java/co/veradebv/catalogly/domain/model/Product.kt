package co.veradebv.catalogly.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val ratingRate: Double,
    val ratingCount: Int
) {
    // Computed properties — business logic lives here, not in the UI
    val formattedPrice: String
        get() = "$${String.format("%.2f", price)}"

    val formattedRating: String
        get() = "$ratingRate ($ratingCount reviews)"

    val isHighlyRated: Boolean
        get() = ratingRate >= 4.0
}