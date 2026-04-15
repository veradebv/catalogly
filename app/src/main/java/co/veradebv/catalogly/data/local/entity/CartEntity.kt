package co.veradebv.catalogly.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cart")
data class CartEntity (
    @PrimaryKey
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int = 1
)