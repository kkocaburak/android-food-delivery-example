package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

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
        viewModel.initializeVM()
    }

    override fun setListeners() {
        restaurantAdapter.apply {
            setOnRestaurantClickListener { restaurantUIModel ->
                viewModel.onRestaurantClicked(restaurantUIModel)
            }

            setOnRestaurantFavoriteClickListener { shouldRestaurantFavorite, restaurantName ->
                viewModel.handleFavoriteRestaurant(shouldRestaurantFavorite, restaurantName)
            }
        }
    }

    override fun setReceivers() {
        observe(viewModel.restaurantListUIModel) {
            binder.recyclerviewRestaurant.adapter = restaurantAdapter.apply {
                submitList(it.restaurantList)
            }
        }
    }
}