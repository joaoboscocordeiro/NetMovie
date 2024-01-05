package com.jbc.appnetmovie.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.presenter.auth.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 27/12/2023.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val rFragment = RegisterFragment()

        val fManager = supportFragmentManager
        val transaction = fManager.beginTransaction()
        //transaction.add(R.id.container, rFragment)
        transaction.commit()
    }
}