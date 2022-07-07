package com.gabin.newsreader.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabin.newsreader.databinding.FragmentNewsFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFeedFragment : Fragment() {

    private lateinit var binding: FragmentNewsFeedBinding
    private val viewModel: NewsFeedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsFeedAdapter = NewsFeedAdapter {
            // Navigate to newsDetails
            findNavController().navigate(NewsFeedFragmentDirections.actionNewsFeedToArticleDetails(it))
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsFeedAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchNewsFeed()   // Fetch news when screen is resumed
                launch {
                    viewModel.newsFlow.collectLatest {
                        newsFeedAdapter.submitList(it)
                    }
                }
                launch {
                    viewModel.loadingFlow.collectLatest {
                        if (it) {
                            binding.spinner.show()
                        } else {
                            binding.spinner.hide()
                        }
                    }
                }
            }
        }
    }
}
