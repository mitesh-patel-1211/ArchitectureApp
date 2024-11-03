package com.app.architectureapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.architectureapp.model.ProductsItem

@TypeConverters(DataConverter::class)
@Database(entities = [ProductsItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}