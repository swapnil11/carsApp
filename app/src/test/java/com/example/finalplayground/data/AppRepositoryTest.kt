package com.example.finalplayground.data

import com.example.finalplayground.BaseTest
import com.example.finalplayground.data.network.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class AppRepositoryTest : BaseTest() {

    @Test
    fun testSuccessResponse() {
        setResponse("response.json")
        runBlocking {
            Assert.assertTrue(
                repository.cars().status == Resource.Status.SUCCESS
            )
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue(
                repository.cars().status == Resource.Status.ERROR
            )
        }
    }

    @Test
    fun testCarItems() {
        setResponse("response.json")
        runBlocking {
            val expectedItems = 20 // in local json file, we have 20 car items.
            Assert.assertEquals(
                repository.cars().data?.size, expectedItems
            )
        }
    }

    @Test
    fun testEmptyResponse() {
        setEmptyResponse()
        runBlocking {
            val expectedItems = 0 // in local json file, we have 20 car items.
            val response = repository.cars()
            Assert.assertTrue(
                response.status == Resource.Status.SUCCESS
            )
            Assert.assertEquals(
                response.data?.size, expectedItems
            )
        }
    }
}
