package com.app.architectureapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@SerializedName("total")
	val total: Int? = null,

	@SerializedName("limit")
	val limit: Int? = null,

	@SerializedName("skip")
	val skip: Int? = null,

	@SerializedName("products")
	val products: List<ProductsItem?>? = null
)
@Entity(tableName = "products_table")
data class ProductsItem(

	@SerializedName("discountPercentage")
	val discountPercentage: String? = null,

	@SerializedName("thumbnail")
	val thumbnail: String? = null,

	@SerializedName("images")
	val images: List<String?>? = null,

	@SerializedName("price")
	val price: Int? = null,

	@SerializedName("rating")
	val rating: String? = null,

	@SerializedName("description")
	val description: String? = null,

	@PrimaryKey(autoGenerate = true)
	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("stock")
	val stock: Int? = null,

	@SerializedName("category")
	val category: String? = null,

	@SerializedName("brand")
	val brand: String? = null
)
