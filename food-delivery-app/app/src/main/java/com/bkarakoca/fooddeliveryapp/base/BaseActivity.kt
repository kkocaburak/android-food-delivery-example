package com.bkarakoca.fooddeliveryapp.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    @get:LayoutRes
    abstract val layoutId: Int

    val binder by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }
}

fun <T> lazyThreadSafetyNone(initializer: () -> T):
        Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)