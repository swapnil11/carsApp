package com.example.finalplayground.domain.respository

import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.domain.model.Car

/**
 * Repository layer for fetching data from either network or db layer.
 */
interface AppRepository {
    /**
     * Fetches the cars from the remote API.
     */
    suspend fun cars(): Resource<List<Car>>
}
