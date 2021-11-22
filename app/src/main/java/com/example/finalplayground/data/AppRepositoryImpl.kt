package com.example.finalplayground.data

import com.example.finalplayground.data.network.CarsApi
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.data.network.ResponseHandler
import com.example.finalplayground.domain.model.Car
import com.example.finalplayground.domain.respository.AppRepository
import javax.inject.Inject

/**
 * Repository layer for fetching data from either network or db layer.
 */
class AppRepositoryImpl @Inject constructor(
    private val api: CarsApi
) : AppRepository {

    /**
     * Fetches the cars from the remote API.
     */
    override suspend fun cars(): Resource<List<Car>> = try {
        ResponseHandler.handleSuccess(api.cars() ?: listOf())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}
