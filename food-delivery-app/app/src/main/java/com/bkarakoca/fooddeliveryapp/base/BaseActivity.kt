package com.bkarakoca.fooddeliveryapp.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bkarakoca.fooddeliveryapp.internal.util.lazyThreadSafetyNone

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    @get:LayoutRes
    abstract val layoutId: Int

    val binder by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }
}