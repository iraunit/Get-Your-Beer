package com.example.getyourbeer

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shyptsolution.codingkaro.Contests.api.MainViewModel
import com.shyptsolution.codingkaro.Contests.api.MainViewModelFactory
import com.shyptsolution.codingkaro.Contests.api.Repository
import com.shyptsolution.getyourbeer.Database.BeerEntity
import com.shyptsolution.getyourbeer.Database.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        // UI and Fragments work here
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).itemIconTintList = null
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.favorite,
                R.id.cart,
                R.id.pastOrder
            )
        )

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //API Work Here
        val repository= Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        var viewModel: MainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllBeers()
        val dataBaseViewModel: ViewModel =ViewModelProvider(this)[ViewModel::class.java]
        viewModel.myResponse.observe(this, Observer {
            for(beer in it){
                var beer= BeerEntity(beer.id,beer.name,beer.tagline,beer.firstBrewed,beer.description,beer.imageUrl,beer.attenuationLevel,beer.foodPairing,(abs(beer.name.hashCode())%102580).toDouble())
                lifecycleScope.launch(Dispatchers.IO){
                    dataBaseViewModel.repository.insertBeer(beer)
                }
            }
        })
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

}