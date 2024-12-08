package com.vitorfg8.smartride.data.model


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("review")
    val review: Review,
    @SerializedName("value")
    val value: Double,
    @SerializedName("vehicle")
    val vehicle: String
)