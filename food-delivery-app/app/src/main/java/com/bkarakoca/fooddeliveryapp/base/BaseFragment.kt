package com.bkarakoca.fooddeliveryapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bkarakoca.fooddeliveryapp.BR
import com.bkarakoca.fooddeliveryapp.internal.extension.observeNonNull
import com.bkarakoca.fooddeliveryapp.internal.extension.showPopup
import com.bkarakoca.fooddeliveryapp.internal.util.lazyThreadSafetyNone
import com.bkarakoca.fooddeliveryapp.navigation.NavigationCommand
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> :
    Fragment() {

    lateinit var binder: B

    @get:LayoutRes
    abstract val layoutId: Int

    @Suppress("UNCHECKED_CAST")
    protected val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this)[persistentViewModelClass]
    }

    abstract fun initialize()

    abstract fun setListeners()

    abstract fun setReceivers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binder.lifecycleOwner = viewLifecycleOwner
        binder.setVariable(BR.viewModel, viewModel)

        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
        observeFailure()

        initialize()
        setListeners()
        setReceivers()
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun observeFailure() {
        viewModel.popup.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { popupModel ->
                requireContext().showPopup(popupModel)
            }
        }
    }

    open fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirection -> {
                findNavController().navigate(command.directions, getExtras())
            }
            is NavigationCommand.Popup -> {
                with(command) {
                    context?.showPopup(model, listener)
                }
            }
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}
