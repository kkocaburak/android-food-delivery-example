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
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binder.recyclerviewRestaurant.apply {
            adapter = restaurantAdapter
            itemAnimator?.changeDuration = 0
        }
    }

    override fun setListeners() {
        restaurantAdapter.apply {
            setOnRestaurantClickListener { restaurantUIModel ->
                viewModel.onRestaurantClicked(restaurantUIModel)
            }

            setOnRestaurantFavoriteClickListener { restaurantUIModel ->
                viewModel.handleFavoriteRestaurant(restaurantUIModel)
            }
        }
    }

    override fun setReceivers() {
        observe(viewModel.restaurantListUIModel) { uiModel ->
            uiModel?.restaurantList?.let { restaurantList ->
                restaurantAdapter.submitList(ArrayList(restaurantList))
            }
        }
    }
}