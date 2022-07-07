package com.gabin.newsreader.di

import com.gabin.newsreader.data.remotesource.NewsReaderRemoteSource
import com.gabin.newsreader.data.remotesource.NewsReaderRemoteSourceImpl
import com.gabin.newsreader.data.repository.NewsReaderRepository
import com.gabin.newsreader.data.repository.NewsReaderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourceBindModule {

    @Binds
    @Singleton
    fun bindNewsReaderRemoteSource(remoteSourceImpl: NewsReaderRemoteSourceImpl): NewsReaderRemoteSource

    @Binds
    @Singleton
    fun bindNewsReaderRepository(repositoryImpl: NewsReaderRepositoryImpl): NewsReaderRepository
}
