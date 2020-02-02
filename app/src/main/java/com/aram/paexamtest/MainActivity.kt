package com.aram.paexamtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.aram.paexamtest.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.bind

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navControler: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        drawerLayout = binding.drawerLayout

        navControler = findNavController(R.id.MyNavHost)

        NavigationUI.setupActionBarWithNavController(this,navControler,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView,navControler)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navControler,drawerLayout)
    }
}
