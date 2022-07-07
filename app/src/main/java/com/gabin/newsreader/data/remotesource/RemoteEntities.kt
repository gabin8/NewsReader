package com.gabin.newsreader.data.remotesource

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class NewsFeedResponse(
    val totalResults: Int,
    val articles: List<NewsArticleEntity>,
)

@Serializable
data class NewsArticleEntity(
    val source: NewsSourceEntity,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    @Contextual
    val publishedAt: Date,
    val content: String,
)

@Serializable
data class NewsSourceEntity(
    val name: String,
)
