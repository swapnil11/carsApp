package com.example.finalplayground.data.network

import com.example.finalplayground.domain.model.Car
import retrofit2.http.GET

/**
 * API class for defining endpoint and request for the application
 */
interface CarsApi {
    /**
     * Suspend function which fetches the [List] of [Car] from the [CARS_ENDPOINT].
     * @return list of [Car]
     */
    @GET(CARS_ENDPOINT)
    suspend fun cars(): List<Car>?

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/swapnil11/074341ac22f3f945e14ef86a52bcc69f/raw/b3f99ac4d857378cf42d2bd49481fef578ea902b/"
        const val IMAGES_BASE_URL = "https://raw.githubusercontent.com/swapnil11/imagesRepo/"
        private const val CARS_ENDPOINT = "cars.json"
    }
}
