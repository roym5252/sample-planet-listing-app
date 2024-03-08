package com.jpm.datasource_remote

import com.jpm.datasource_remote.model.RemotePlanetListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(PLANETS)
    suspend fun getPlanets(@Query("page") page:Long): RemotePlanetListResponse?
}