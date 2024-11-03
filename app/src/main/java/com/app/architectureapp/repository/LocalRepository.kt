package com.app.architectureapp.repository

import com.app.architectureapp.model.ProductsItem
import com.app.architectureapp.room.ProductDao
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAllProducts() = productDao.getProducts()

    suspend fun addProducts(productsItem: ProductsItem) = productDao.addProducts(productsItem)

    suspend fun updateProducts(productsItem: ProductsItem) {
        productDao.update(productsItem)
    }
}