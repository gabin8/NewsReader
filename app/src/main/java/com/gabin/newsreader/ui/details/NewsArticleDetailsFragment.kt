package com.gabin.newsreader.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.size.Scale
import com.gabin.newsreader.R
import com.gabin.newsreader.databinding.FragmentArticleDetailsBinding
import java.text.SimpleDateFormat

class NewsArticleDetailsFragment: Fragment() {

    private lateinit var binding: FragmentArticleDetailsBinding
    private val args: NewsArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsItem = args.newsItem

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar = binding.articleToolbar
        toolbar.title = newsItem.newsSource.source
        toolbar.setOnMenuItemClickListener {
            it.onNavDestinationSelected(navController)
        }

        toolbar.setupWithNavController(navController, appBarConfiguration)

        with(binding) {
            articleImage.load(newsItem.imageUrl) {
                scale(Scale.FILL)
            }
            articleTitle.text = newsItem.title
            articleDescription.text = newsItem.description
            articleDate.text = getString(R.string.published_at, SimpleDateFormat("yyyy-MM-dd").format(newsItem.publishedAt))
            articleOpenBtn.setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(newsItem.url)
                    startActivity(this)
                }
            }
        }
    }
}
