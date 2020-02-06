package com.sunsosay.wongnaiassignment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.sunsosay.wongnaiassignment.presentation.di.presentationModules
import com.sunsosay.wongnaiassignment.services.di.remoteModule
import com.sunsosay.wongnaiassignment.services.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application(), Application.ActivityLifecycleCallbacks {
    companion object {
        lateinit var mApplicationContext: Activity
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@Application)
            androidLogger()
            modules(listOf(repositoryModule, remoteModule, presentationModules))
        }
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity) {
        activity.let { mApplicationContext = it }
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.let { mApplicationContext = it }
    }

}
