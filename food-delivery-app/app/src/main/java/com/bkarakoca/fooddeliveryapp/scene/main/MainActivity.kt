package com.bkarakoca.fooddeliveryapp.scene.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseActivity
import com.bkarakoca.fooddeliveryapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    // You can use this method for navigating from Splash Activity
    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override val layoutId get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.lifecycleOwner = this
    }
}
