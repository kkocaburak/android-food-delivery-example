package com.bkarakoca.fooddeliveryapp.scene.splash

import android.os.Bundle
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseActivity
import com.bkarakoca.fooddeliveryapp.databinding.ActivitySplashMainBinding
import com.bkarakoca.fooddeliveryapp.scene.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashMainBinding>() {

    override val layoutId: Int = R.layout.activity_splash_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startMainActivity()
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
