package com.jpm.core.data.repository

import com.jpm.datasource_local.entity.LocalPlanet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    /*fun getPlanets():Flow<PagingData<Planet>>*/

    suspend fun savePlanets(localPlanet: List<LocalPlanet>): Flow<Boolean>
}