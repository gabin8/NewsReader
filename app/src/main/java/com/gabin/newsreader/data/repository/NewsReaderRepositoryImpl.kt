package com.gabin.newsreader.data.repository

import com.gabin.newsreader.data.models.NewsFeed
import com.gabin.newsreader.data.remotesource.NewsReaderRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsReaderRepositoryImpl @Inject constructor(private val remoteSource: NewsReaderRemoteSource) :
        NewsReaderRepository {

    override fun fetchNewsFeed(): Flow<NewsFeed> {
        return remoteSource.fetchNewsFeed()
    }
}
