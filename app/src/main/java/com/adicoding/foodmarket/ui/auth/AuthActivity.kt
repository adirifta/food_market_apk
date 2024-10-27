package com.adicoding.foodmarket.ui.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.adicoding.foodmarket.R

class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        Log.d(TAG, "AuthActivity created")

        navController = supportFragmentManager.findFragmentById(R.id.authHostFragment)!!.findNavController()

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == 2) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()

            navController.navigate(R.id.action_signup, null, navOptions)
        }
    }
}
