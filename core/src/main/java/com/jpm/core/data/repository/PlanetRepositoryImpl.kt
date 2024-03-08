package com.jpm.core.data.repository
import com.jpm.core.data.repository.datasource.LocalDataSource
import com.jpm.datasource_local.entity.LocalPlanet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : PlanetRepository {
    override suspend fun savePlanets(localPlanet: List<LocalPlanet>): Flow<Boolean> = localDataSource.insertPlanets(localPlanet)

}