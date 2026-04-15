package co.veradebv.catalogly.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.veradebv.catalogly.data.local.entity.CartEntity
import co.veradebv.catalogly.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CartEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}