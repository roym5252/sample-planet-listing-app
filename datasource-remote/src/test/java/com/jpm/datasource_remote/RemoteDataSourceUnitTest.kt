package com.jpm.datasource_remote

import com.google.gson.stream.MalformedJsonException
import com.jpm.datasource_remote.model.RemotePlanet
import com.jpm.datasource_remote.util.MainCoroutineRule
import com.jpm.datasource_remote.util.validJsonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: APIService
    private lateinit var apiHelper: ApiHelper

    @Before
    fun setUp() {

        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)

        apiHelper = ApiHelperImpl(apiClient)
    }


    @Test
    fun `get planets proper response test`() = runTest {
        val response = getRemotePlanetsData(200, validJsonResponse)
        assertEquals(true, !response.isNullOrEmpty())
        assertEquals(true, response!!.size==10)
        assertEquals(true, response[0].name=="Mon Cala")
    }

    @Test
    fun `get planets empty response test`() = runTest {
        val response = getRemotePlanetsData(200, "{}")
        assertEquals(true, response.isNullOrEmpty())
    }

    @Test
    fun `get planets http exception for error code test`() = runTest {

        val result = try {
            getRemotePlanetsData(404, "{}")
        } catch (exception: Exception) {
            exception
        }

        assertTrue(result is HttpException)
    }

    @Test
    fun `get planets malformed exception test`() = runTest {

        val result = try {
            getRemotePlanetsData(200, "{[[[[]")
        } catch (exception: Exception) {
            exception
        }

        assertTrue(result is MalformedJsonException)
    }

    private suspend fun getRemotePlanetsData(errorCode: Int, body: String): List<RemotePlanet>? {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(errorCode)
            .setBody(body)

        mockWebServer.enqueue(mockResponse)

        val apiHelper = apiHelper.getPlanets1(1)
        return apiHelper?.remotePlanets
    }
}