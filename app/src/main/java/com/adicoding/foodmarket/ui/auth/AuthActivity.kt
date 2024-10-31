package com.adicoding.foodmarket.ui.auth

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.adicoding.foodmarket.R

class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navController = supportFragmentManager.findFragmentById(R.id.authHostFragment)!!.findNavController()

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == 2) {
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()

            navController.navigate(R.id.action_signup, null, navOptions)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun toolbarSignUp() {
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register and eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun toolbarSignUpAddress() {
        toolbar.title = "Address"
        toolbar.subtitle = "Make sure it's valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess() {
        toolbar.visibility = View.GONE
    }
}
