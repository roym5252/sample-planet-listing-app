package com.jpm.core.data.repository.datasource

import androidx.paging.PagingSource
import com.jpm.datasource_local.database.AppDatabase
import com.jpm.datasource_local.entity.LocalPlanet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase): LocalDataSource {

    override suspend fun insertPlanets(planets: List<LocalPlanet>):Flow<Boolean> {

        return flow {
            appDatabase.localPlanetDao().insert(planets)
            emit(true)
        }
    }

    override fun insertPlanet1(planets: List<LocalPlanet>) {
        appDatabase.localPlanetDao().insert(planets)
    }

    override suspend fun clearAllPlanets():Flow<Boolean>  {

        return flow {
            appDatabase.localPlanetDao().clearAll()
            emit(true)
        }
    }

    override suspend fun getPlanets(): Flow<List<LocalPlanet>> {
        TODO("Not yet implemented")
    }

    override fun pagingSource(): PagingSource<Int, LocalPlanet> {
        return appDatabase.localPlanetDao().pagingSource()
    }
}