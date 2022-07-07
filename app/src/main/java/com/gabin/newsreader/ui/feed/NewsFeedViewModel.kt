package com.gabin.newsreader.ui.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabin.newsreader.data.models.NewsItem
import com.gabin.newsreader.data.repository.NewsReaderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val repository: NewsReaderRepository,
) : ViewModel() {

    private val _newsFlow = MutableSharedFlow<List<NewsItem>>()
    val newsFlow: Flow<List<NewsItem>> = _newsFlow

    private val _loadingStateFlow = MutableStateFlow(false)
    val loadingFlow: Flow<Boolean> = _loadingStateFlow

    fun fetchNewsFeed() {
        viewModelScope.launch {
            _loadingStateFlow.emit(true)
            repository.fetchNewsFeed()
                .catch {
                    Log.e(TAG, "Got error: $it")
                    _loadingStateFlow.emit(false)
                }
                .collectLatest {
                    _newsFlow.emit(it.items)
                    _loadingStateFlow.emit(false)
                }
        }
    }

    companion object {

        const val TAG = "NewsFeedViewModel"
    }

}
