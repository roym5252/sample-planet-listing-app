package com.jpm.datasource_remote.di

import android.content.Context
import com.jpm.datasource_remote.APIService
import com.jpm.datasource_remote.ApiHelper
import com.jpm.datasource_remote.ApiHelperImpl
import com.jpm.datasource_remote.BuildConfig
import com.jpm.datasource_remote.MockClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor:HttpLoggingInterceptor, mockClient: MockClient): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(mockClient).build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): APIService {

        return retrofit.create(
            APIService::class.java
        )
    }

    @Provides
    fun provideMockClient(@ApplicationContext appContext: Context): MockClient {
        return MockClient(appContext)
    }

    @Provides
    fun provideGsonConverterFactory():GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    }

    @Provides
    fun provideApiHelper(apiService: APIService): ApiHelper {
        return ApiHelperImpl(apiService)
    }
}