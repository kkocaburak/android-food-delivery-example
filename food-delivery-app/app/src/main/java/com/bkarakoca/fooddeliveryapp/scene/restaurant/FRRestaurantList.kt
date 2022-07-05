package com.bkarakoca.fooddeliveryapp.scene.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseFragment
import com.bkarakoca.fooddeliveryapp.databinding.FragmentRestaurantListBinding
import com.bkarakoca.fooddeliveryapp.internal.extension.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FRRestaurantList : BaseFragment<FRRestaurantListVM, FragmentRestaurantListBinding>() {

    override val layoutId get() = R.layout.fragment_restaurant_list

    @Inject
    lateinit var restaurantAdapter: AdapterRestaurantList

    override fun initialize() {
        viewModel.initializeVM(requireContext())
    }

    override fun setReceivers() {
        observe(viewModel.restaurantListUIModel) {
            binder.recyclerviewRestaurant.adapter = restaurantAdapter.apply {
                submitList(it.restaurantList)
            }
        }
    }
}