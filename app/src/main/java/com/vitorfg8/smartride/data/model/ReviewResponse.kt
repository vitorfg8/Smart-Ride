package com.vitorfg8.smartride.data.model


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("rating")
    val rating: Int
)