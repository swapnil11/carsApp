package com.example.finalplayground.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val id: String,
    val make: CarMake,
    val color: String,
    val year: Int,
    val configuration: CarConfiguration,
    val origin: String? = null,
    val mpg: Int? = null,
    val image: String? = null,
    val price: Int
) {
    fun getFormattedTitle(): String {
        return String.format(CAR_TITLE_PATTERN, year, make.manufacturer, make.model)
    }

    companion object {
        private const val CAR_TITLE_PATTERN = "%s %s %s"
    }
}

@Serializable
data class CarMake(
    val manufacturer: String,
    val model: String
)

@Serializable
data class CarConfiguration(
    val body: String,
    val cylinders: Int? = null,
    val horsepower: Int? = null,
)
