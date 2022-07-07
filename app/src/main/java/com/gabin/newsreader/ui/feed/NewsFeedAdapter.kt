package com.gabin.newsreader.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.gabin.newsreader.R
import com.gabin.newsreader.data.models.NewsItem
import com.gabin.newsreader.databinding.ItemNewsArticleBinding
import com.gabin.newsreader.ui.feed.list.NewsItemDiffCallback

class NewsFeedAdapter(private val articleClickListener: OnNewsArticleClickListener) :
        ListAdapter<NewsItem, NewsFeedAdapter.NewsItemViewHolder>(NewsItemDiffCallback()) {

    fun interface OnNewsArticleClickListener {

        fun onNewsArticleClicked(newsItem: NewsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = ItemNewsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
        holder.itemView.setOnClickListener {
            articleClickListener.onNewsArticleClicked(newsItem)
        }
    }

    class NewsItemViewHolder(private val binding: ItemNewsArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: NewsItem) {
            with(binding) {
                articleImage.load(newsItem.imageUrl) {
                    crossfade(true)
                    scale(Scale.FILL)
                }
                articleTitle.text = newsItem.title
                articleDescription.text = newsItem.description
                articleSource.text = itemView.resources.getString(R.string.source, newsItem.newsSource.source)
            }
        }
    }


}
