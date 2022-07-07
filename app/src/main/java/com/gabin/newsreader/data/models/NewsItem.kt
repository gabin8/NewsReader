package com.gabin.newsreader.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class NewsItem(
    val author: String?,
    val newsSource: NewsSource,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String?,
    val publishedAt: Date,
    val content: String,
) : Parcelable

@Parcelize
data class NewsSource(
    val source: String,
) : Parcelable
