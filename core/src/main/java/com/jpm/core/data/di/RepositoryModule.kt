package com.jpm.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jpm.core.data.mediator.DataMediator
import com.jpm.core.data.repository.PlanetRepository
import com.jpm.core.data.repository.PlanetRepositoryImpl
import com.jpm.core.data.repository.datasource.LocalDataSource
import com.jpm.core.data.repository.datasource.RemoteDataSource
import com.jpm.datasource_local.entity.LocalPlanet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun providePlanetRepository(localDataSource: LocalDataSource): PlanetRepository {
        return PlanetRepositoryImpl(localDataSource)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    fun providePlantPager(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): Pager<Int, LocalPlanet> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = DataMediator(
                localDataSource,
                remoteDataSource
            ),
            pagingSourceFactory = {
                localDataSource.pagingSource()
            }
        )
    }
}