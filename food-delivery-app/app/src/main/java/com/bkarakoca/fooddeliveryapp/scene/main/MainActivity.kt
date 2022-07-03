package com.bkarakoca.fooddeliveryapp.scene.main

import android.content.Context
import android.content.Intent
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseActivity
import com.bkarakoca.fooddeliveryapp.databinding.ActivityMainBinding
import com.bkarakoca.fooddeliveryapp.internal.extension.injectVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override val layoutId get() = R.layout.activity_main
}
