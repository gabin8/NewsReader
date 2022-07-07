package com.gabin.newsreader.ui.feed.list

import androidx.recyclerview.widget.DiffUtil
import com.gabin.newsreader.data.models.NewsItem

class NewsItemDiffCallback : DiffUtil.ItemCallback<NewsItem>() {

    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}
