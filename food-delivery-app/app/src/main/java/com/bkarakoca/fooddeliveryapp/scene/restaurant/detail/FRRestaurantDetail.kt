package com.bkarakoca.fooddeliveryapp.scene.restaurant.detail

import androidx.navigation.fragment.navArgs
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseFragment
import com.bkarakoca.fooddeliveryapp.databinding.FragmentRestaurantDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FRRestaurantDetail : BaseFragment<FRRestaurantDetailVM, FragmentRestaurantDetailBinding>() {

    override val layoutId: Int get() = R.layout.fragment_restaurant_detail

    private val args by navArgs<FRRestaurantDetailArgs>()

    override fun initialize() {
        viewModel.initializeVM(args)
        println(args.restaurantUIModel.restaurantName)
    }

    override fun setListeners() { }

    override fun setReceivers() { }

}