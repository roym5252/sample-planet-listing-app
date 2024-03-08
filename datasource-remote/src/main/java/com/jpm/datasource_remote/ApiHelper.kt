package com.jpm.datasource_remote

import com.jpm.datasource_remote.model.RemotePlanetListResponse
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getPlanets(page:Long) : Flow<RemotePlanetListResponse?>
    suspend fun getPlanets1(page:Long) : RemotePlanetListResponse?
}