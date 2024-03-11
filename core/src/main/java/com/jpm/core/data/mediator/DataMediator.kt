package com.jpm.core.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jpm.core.data.model.Planet
import com.jpm.core.data.repository.datasource.LocalDataSource
import com.jpm.core.data.repository.datasource.RemoteDataSource
import com.jpm.datasource_local.entity.LocalPlanet
import com.jpm.util.TaskResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalPagingApi::class)
class DataMediator @Inject constructor(
    @Named("LocalDataSource") val localDataSource: LocalDataSource,
    @Named("RemoteDataSource") val remoteDataSourceImpl: RemoteDataSource
) : RemoteMediator<Int, LocalPlanet>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalPlanet>
    ): MediatorResult {

        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        2
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val remotePlanetsResult = remoteDataSourceImpl.getPlanets1(
                pageCount = loadKey
            )

            if (remotePlanetsResult is TaskResult.Success) {

                if (loadKey>1){
                    //Dummy delay to emulate time consuming operation and to make bottom loader visible during pagination.
                    delay(3000)
                }

                val localPlanets = remotePlanetsResult.data?.map { remotePlanet ->
                    Planet.fromRemotePlanetToLocalPlanet(remotePlanet)
                }

                localPlanets?.let {
                    localDataSource.insertPlanets(localPlanets).flowOn(Dispatchers.IO).collect()
                }

                MediatorResult.Success(
                    endOfPaginationReached = false
                )

            } else {
                MediatorResult.Error(InterruptedException())
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}