package com.pg.cloudcleaner.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.pg.cloudcleaner.app.AppData
import com.pg.cloudcleaner.app.CloudCleanerApp
import timber.log.Timber

@ExperimentalFoundationApi
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppData.instance().initNavController(rememberNavController())
            CloudCleanerApp()
        }
//        startActivity(Intent(this, MainActivity2::class.java))
        Timber.e("onCreate")
    }

    override fun onRestart() {
        Timber.e("onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Timber.e("onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.e("onPause")
        super.onPause()
    }

    override fun onStart() {
        Timber.e("onStart")
        super.onStart()
    }

    override fun onStop() {
        Timber.e("onStop")
        super.onStop()
    }
}
