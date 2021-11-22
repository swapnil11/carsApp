package com.example.finalplayground.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.finalplayground.data.network.Resource
import com.example.finalplayground.domain.model.Car
import com.example.finalplayground.domain.usecases.CarUseCase
import com.example.finalplayground.ui.CarListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CarListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var carUseCase: CarUseCase

    private lateinit var carListViewModel: CarListViewModel

    var cars = readJson("response.json") // Reading mock json

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun testCarsViewModel() {
        runBlocking {

            coEvery { carUseCase.cars() } returns Resource.success(cars)
            carListViewModel = CarListViewModel(carUseCase)

            carListViewModel.remoteCars.observeForever {
                if (it.status == Resource.Status.SUCCESS) {
                    Assert.assertEquals(cars, it.data)
                }
            }
        }
    }

    private fun readJson(fileName: String): List<Car> {
        val input = this::class.java.classLoader?.getResourceAsStream(fileName) ?: return listOf()
        return Json.decodeFromStream(input)
    }
}
