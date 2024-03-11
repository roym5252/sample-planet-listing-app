package com.jpm.core.data.repository.datasource

import androidx.paging.PagingSource
import com.jpm.datasource_local.entity.LocalPlanet
import kotlinx.coroutines.flow.Flow

interface LocalDataSource:DataSource {
    suspend fun insertPlanets(planets:List<LocalPlanet>): Flow<Boolean>

    fun insertPlanet1(planets:List<LocalPlanet>)

    suspend fun clearAllPlanets():Flow<Boolean>

    suspend fun getPlanets(): Flow<List<LocalPlanet>>

    fun pagingSource(): PagingSource<Int, LocalPlanet>

    fun getPlanet(id:Long): Flow<LocalPlanet>
}