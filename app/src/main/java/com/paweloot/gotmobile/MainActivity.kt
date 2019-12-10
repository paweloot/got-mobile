package com.paweloot.gotmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        setUpNavController()
    }

    private fun setUpNavController() {
        navController = findNavController(R.id.nav_host_fragment)

        appViewModel.getNewDestination().observe(this, Observer {
            navigateTo(it)
        })
    }

    private fun navigateTo(destination: Int) {
        navController.navigate(destination)
    }
}
