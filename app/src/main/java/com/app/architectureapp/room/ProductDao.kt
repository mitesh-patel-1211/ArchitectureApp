package com.app.architectureapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.architectureapp.model.ProductsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products_table")
    fun getProducts(): Flow<List<ProductsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(productsItem: ProductsItem)

    @Update
    suspend fun update(productsItem: ProductsItem)
}