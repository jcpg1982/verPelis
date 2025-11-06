package pe.com.master.machines.verpelis.di

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LaunchApp : Application() {

    private val TAG = LaunchApp::class.java.simpleName

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)
    }

}