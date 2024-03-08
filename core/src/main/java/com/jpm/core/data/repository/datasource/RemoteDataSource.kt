package com.jpm.core.data.repository.datasource

import com.jpm.datasource_remote.model.RemotePlanet
import com.jpm.util.TaskResult
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getPlanets(pageCount:Long): Flow<TaskResult<List<RemotePlanet>?>>
    suspend fun getPlanets1(pageCount:Long): TaskResult<List<RemotePlanet>?>
}