package com.jpm.core.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.jpm.core.data.model.Planet
import com.jpm.datasource_local.entity.LocalPlanet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlanetPagingDataUseCase @Inject constructor(private val pager: Pager<Int, LocalPlanet>) {
    operator fun invoke(): Flow<PagingData<Planet>> {
        return pager.flow
            .map { pagingData ->
                pagingData.map {
                    Planet.fromLocalPlanet(it)
                }
            }
    }
}