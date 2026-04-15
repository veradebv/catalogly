package co.veradebv.catalogly.di

import android.content.Context
import androidx.room.Room
import co.veradebv.catalogly.data.local.db.AppDatabase
import co.veradebv.catalogly.data.local.db.CartDao
import co.veradebv.catalogly.data.local.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "catalogly_db"
    ).build()

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao = appDatabase.productDao()

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CartDao = appDatabase.cartDao()
}