package com.vitorfg8.smartride.data.model.riderequest


import com.google.gson.annotations.SerializedName

data class OptionResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("review")
    val reviewResponse: ReviewResponse,
    @SerializedName("value")
    val value: Double,
    @SerializedName("vehicle")
    val vehicle: String
)