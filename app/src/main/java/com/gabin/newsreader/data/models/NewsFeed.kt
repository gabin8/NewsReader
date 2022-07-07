package com.gabin.newsreader.data.models

data class NewsFeed(
    val totalResults: Int,
    val items: List<NewsItem>,
)
