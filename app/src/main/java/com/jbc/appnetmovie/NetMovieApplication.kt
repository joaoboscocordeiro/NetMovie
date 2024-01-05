package com.jbc.appnetmovie

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp


/*
 * Created by Joao Bosco on 04/01/24.
 */
@HiltAndroidApp
class NetMovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}