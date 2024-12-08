package com.vitorfg8.smartride.domain.model


data class Option(
    val description: String,
    val id: Int,
    val name: String,
    val review: Review,
    val value: Double,
    val vehicle: String
)