package com.gabin.newsreader.data.remotesource

import com.gabin.newsreader.BuildConfig
import com.gabin.newsreader.data.models.NewsFeed
import com.gabin.newsreader.data.models.NewsItem
import com.gabin.newsreader.data.models.NewsSource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsReaderRemoteSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : NewsReaderRemoteSource {

    override fun fetchNewsFeed(): Flow<NewsFeed> = flow {
        val response: NewsFeedResponse = httpClient.get(NEWS_URL).body()
        val newsFeed = NewsFeed(
            response.totalResults,
            response.articles.map { it.toNewsItem() }
        )
        emit(newsFeed)
    }

    private fun NewsArticleEntity.toNewsItem(): NewsItem {
        return NewsItem(
            author,
            NewsSource(source.name),
            title,
            description,
            url,
            urlToImage,
            publishedAt,
            content
        )
    }

    companion object {

        const val NEWS_URL =
            "https://newsapi.org/v2/everything?q=tesla&sortBy=publishedAt&apiKey=${BuildConfig.NEWS_API_KEY}"
    }
}
