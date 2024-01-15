package com.jbc.appnetmovie.presenter.auth.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jbc.appnetmovie.R
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 15/01/2024.
 */
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}