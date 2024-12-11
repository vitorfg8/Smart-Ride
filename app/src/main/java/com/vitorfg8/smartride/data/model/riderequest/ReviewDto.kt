package com.vitorfg8.smartride.data.model.riderequest


import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("rating")
    val rating: Int
)