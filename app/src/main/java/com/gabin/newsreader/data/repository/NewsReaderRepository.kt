package com.gabin.newsreader.data.repository

import com.gabin.newsreader.data.models.NewsFeed
import kotlinx.coroutines.flow.Flow

interface NewsReaderRepository {
    fun fetchNewsFeed(): Flow<NewsFeed>
}
