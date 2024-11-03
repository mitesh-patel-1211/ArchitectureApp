package com.app.architectureapp.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@SerializedName("code")
	val code: Int? = null,

	@SerializedName("message")
	val message: String? = null,

	@SerializedName("status")
	val status: String? = null
)
