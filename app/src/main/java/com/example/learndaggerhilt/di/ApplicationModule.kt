package com.example.learndaggerhilt.di

import com.example.learndaggerhilt.BuildConfig
import com.example.learndaggerhilt.data.remote.CurrencyApiService
import com.example.learndaggerhilt.data.remote.GithubService
import com.example.learndaggerhilt.utils.AppDispatcherProvider
import com.example.learndaggerhilt.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor().also { it.setLevel(HttpLoggingInterceptor.Level.BODY) }
        if (BuildConfig.DEBUG) okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGithubService(client: OkHttpClient): GithubService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyApiService(client: OkHttpClient): CurrencyApiService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_CURRENCY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CurrencyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider {
        return AppDispatcherProvider()
    }

}

