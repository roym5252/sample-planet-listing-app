package com.jpm.datasource_remote

import com.jpm.datasource_remote.model.RemotePlanetListResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: APIService):ApiHelper {
    override suspend fun getPlanets(page: Long) = flow {
        emit(apiService.getPlanets(page))
    }

    override suspend fun getPlanets1(page: Long): RemotePlanetListResponse? {
        return apiService.getPlanets(page)
    }
}