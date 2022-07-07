package com.gabin.newsreader.data.remotesource

import com.gabin.newsreader.data.models.NewsFeed
import kotlinx.coroutines.flow.Flow

interface NewsReaderRemoteSource {
    fun fetchNewsFeed(): Flow<NewsFeed>
}
