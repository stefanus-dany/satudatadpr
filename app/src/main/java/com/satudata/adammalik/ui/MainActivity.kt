package com.satudata.adammalik.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.ActivityMainBinding

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
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_data,
                R.id.navigation_profile
            )
        )

//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if(navView.selectedItemId == R.id.navigation_home){
            navController.setGraph(R.navigation.home_navigation)
        }
        //setGraph based on navview clicked
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    navController.setGraph(R.navigation.home_navigation)
                }

                R.id.navigation_dashboard -> {
                    navController.setGraph(R.navigation.dashboard_navigation)
                }

                R.id.navigation_data -> {
                    navController.setGraph(R.navigation.data_navigation)
                }

                R.id.navigation_profile -> {

                }
            }
            return@setOnItemSelectedListener true
        }
    }
}