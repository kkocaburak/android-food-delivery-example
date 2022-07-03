package com.bkarakoca.fooddeliveryapp.internal.extension

import com.bkarakoca.fooddeliveryapp.BR
import com.bkarakoca.fooddeliveryapp.base.BaseActivity
import com.bkarakoca.fooddeliveryapp.base.BaseFragment
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel

inline fun <reified VM : BaseViewModel> Lazy<VM>.registerBinding(
    owner: BaseFragment<*>
) {
    with(owner) {
        this@registerBinding.value.let { viewModel ->
            binder.lifecycleOwner = this
            binder.setVariable(BR.viewModel, viewModel)
            binder.executePendingBindings()
        }
    }
}

inline fun <reified VM : BaseViewModel> Lazy<VM>.registerBinding(
    owner: BaseActivity<*>
) {
    with(owner) {
        this@registerBinding.value.let { viewModel ->
            binder.lifecycleOwner = this
            binder.setVariable(BR.viewModel, viewModel)
            binder.executePendingBindings()
        }
    }
}

inline fun <reified VM : BaseViewModel> Lazy<VM>.registerObservers(
    owner: BaseFragment<*>
) {
    with(owner) {
        this@registerObservers.value.let { viewModel ->
            /** navigation */
            viewModel.navigation.observeNonNull(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { command ->
                    handleNavigation(command)
                }
            }

            /** popup */
            viewModel.popup.observeNonNull(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { popupModel ->
                    requireContext().showPopup(popupModel)
                }
            }
        }
    }
}