package com.example.aisletechchallenge.ui.home.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aisletechchallenge.R
import com.example.aisletechchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        intent.extras?.getString("token")
        var bundle : Bundle = Bundle()
        bundle.putString("token", intent.extras?.getString("token"))

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        navGraph.setStartDestination(R.id.navigation_notes)
        navController.setGraph(navGraph, bundle)
        navView.setupWithNavController(navController)
        navView.setBadge(R.id.navigation_notes,"9")
        navView.setBadge(R.id.navigation_dashboard,"50+")

    }

    fun BottomNavigationView.setBadge(tabResId: Int, badgeValue: String) {
        getOrCreateBadge(this, tabResId)?.let { badge ->
                badge.text = "$badgeValue"
                View.VISIBLE
        }
    }

    private fun getOrCreateBadge(bottomBar: View, tabResId: Int): TextView? {
        val parentView = bottomBar.findViewById<ViewGroup>(tabResId)
        return parentView?.let {
            var badge = parentView.findViewById<TextView>(R.id.badge)
            if (badge == null) {
                LayoutInflater.from(parentView.context).inflate(R.layout.badge, parentView, true)
                badge = parentView.findViewById(R.id.badge)
            }
            badge
        }
    }

}