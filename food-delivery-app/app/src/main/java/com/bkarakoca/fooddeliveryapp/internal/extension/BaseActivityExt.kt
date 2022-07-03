package com.bkarakoca.fooddeliveryapp.internal.extension

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelLazy
import com.bkarakoca.fooddeliveryapp.base.BaseActivity
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel

@MainThread
inline fun <reified VM : BaseViewModel> BaseActivity<*>.injectVM(): Lazy<VM> {
    return ViewModelLazy(VM::class, { viewModelStore }, { defaultViewModelProviderFactory }).also {
        it.registerBinding(this)
    }
}