package co.veradebv.catalogly.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.veradebv.catalogly.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCartItems(): Flow<List<CartEntity>>

    @Query("SELECT * FROM cart WHERE productId = :productId")
    suspend fun getCartItem(productId: Int): CartEntity?

    // suspend (not Flow) because we only need the value once
    // not a continuous stream — used to check if item exists before inserting

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartEntity)

    @Update
    suspend fun updateCartItem(cartItem: CartEntity)

    @Query("DELETE FROM cart WHERE productId = :productId")
    suspend fun removeCartItem(productId: Int)

    @Query("DELETE FROM cart")
    suspend fun clearCart()

    @Query("SELECT COUNT(*) FROM cart")
    suspend fun getCartItemCount(): Flow<Int>
}