package com.jpm.core.data.repository.datasource

import com.jpm.datasource_remote.ApiHelper
import com.jpm.datasource_remote.model.RemotePlanet
import com.jpm.util.ErrorType
import com.jpm.util.TaskResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSourceImpl(private val apiHelper: ApiHelper) : RemoteDataSource {
    override suspend fun getPlanets(pageCount:Long): Flow<TaskResult<List<RemotePlanet>?>> {

        return flow {

            apiHelper.getPlanets(pageCount)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    emit(TaskResult.Error(ErrorType.UNEXPECTED))
                }
                .collect {

                    it?.let {
                        emit(TaskResult.Success(it.remotePlanets))
                    }?: kotlin.run {
                        TaskResult.Error(ErrorType.UNEXPECTED)
                    }
                }
        }
    }

    override suspend fun getPlanets1(pageCount: Long): TaskResult<List<RemotePlanet>?> {

        val result = apiHelper.getPlanets1(pageCount)

        return if (result!=null){
            TaskResult.Success(result.remotePlanets)
        }else{
            TaskResult.Error(ErrorType.UNEXPECTED)
        }
    }
}