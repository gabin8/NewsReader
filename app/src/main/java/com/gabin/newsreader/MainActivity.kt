package com.gabin.newsreader

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainNavHost)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
