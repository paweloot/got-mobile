package com.paweloot.gotmobile

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.paweloot.gotmobile.mtnrange.view.MtnRangeFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)

        appViewModel.newDestination.observe(this, Observer {
            navigateTo(it)
        })
    }

    private fun navigateTo(destination: Int) {
        navController.navigate(destination)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (navController.currentDestination?.id == R.id.mtnRangeFragment) {
            showLogOutAlert()
            return true
        }

        navController.navigateUp()
        return true
    }

    fun showLogOutAlert() {
        val alert = AlertDialog.Builder(this)
            .setMessage("Ta akcja spowoduje wylogowanie. Na pewno chcesz kontynuować?")
            .setPositiveButton("Tak") { dialogInterface, _ ->
                appViewModel.logOutTourist()
                dialogInterface.dismiss()

                appViewModel.newDestination.value =
                    MtnRangeFragmentDirections.actionMtnRangeFragmentToLoginFragment().actionId
            }
            .setNegativeButton("Nie") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

        alert.show()
    }
}
