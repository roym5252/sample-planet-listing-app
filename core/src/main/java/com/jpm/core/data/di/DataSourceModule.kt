package com.jpm.core.data.di

import com.jpm.core.data.repository.datasource.LocalDataSource
import com.jpm.core.data.repository.datasource.LocalDataSourceImpl
import com.jpm.core.data.repository.datasource.RemoteDataSource
import com.jpm.core.data.repository.datasource.RemoteDataSourceImpl
import com.jpm.datasource_local.database.AppDatabase
import com.jpm.datasource_remote.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideRemoteDataSource(apiHelper: ApiHelper): RemoteDataSource {
        return RemoteDataSourceImpl(apiHelper)
    }

    @Provides
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSourceImpl(appDatabase)
    }
}