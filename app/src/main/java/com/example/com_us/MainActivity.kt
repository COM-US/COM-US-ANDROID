package com.example.com_us

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.com_us.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_questions, R.id.navigation_profile
            )
        )

        setSupportActionBar(binding.includeToolbar.toolbar)
        supportActionBar?.hide()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.itemIconTintList = null

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_questions -> {
                    navController.navigate(R.id.navigation_questions)
                    true
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    true
                }
                R.id.navigation_block -> {
                    navController.navigate(R.id.blockFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun changeNavItemColor(colorResId: Int) {
        binding.navView.itemIconTintList = ContextCompat.getColorStateList(this, colorResId)
    }

}