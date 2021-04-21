package com.example.learndaggerhilt.di

import com.example.learndaggerhilt.data.repository.CurrencyRepository
import com.example.learndaggerhilt.data.repository.CurrencyRepositoryImpl
import com.example.learndaggerhilt.data.repository.GithubRepository
import com.example.learndaggerhilt.data.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository

    @Binds
    @Singleton
    abstract fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

}