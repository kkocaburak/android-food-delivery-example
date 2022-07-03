package com.bkarakoca.fooddeliveryapp.internal.extension

import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.bkarakoca.fooddeliveryapp.base.BaseFragment
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel

@MainThread
inline fun <reified VM : BaseViewModel, B : ViewDataBinding>
        BaseFragment<B>.activityViewModels(): Lazy<VM> {
    return activityViewModels<VM>().apply {
        registerObservers(this@activityViewModels)
        registerBinding(this@activityViewModels)
    }
}

@MainThread
inline fun <reified VM : BaseViewModel>
        BaseFragment<*>.injectVM(): Lazy<VM> {
    return viewModels<VM>().apply {
        registerObservers(this@injectVM)
        registerBinding(this@injectVM)
    }
}

@MainThread
inline fun <reified VM : BaseViewModel, B : ViewDataBinding>
        BaseFragment<B>.parentViewModels(): Lazy<VM> {
    return requireParentFragment().viewModels<VM>().apply {
        registerObservers(this@parentViewModels)
        registerBinding(this@parentViewModels)
    }
}

@MainThread
inline fun <reified VM : BaseViewModel, B : ViewDataBinding>
        BaseFragment<B>.navGraphViewModels(@IdRes navGraphId: Int): Lazy<VM> {
    return navGraphViewModels<VM>(navGraphId).apply {
        registerObservers(this@navGraphViewModels)
        registerBinding(this@navGraphViewModels)
    }
}