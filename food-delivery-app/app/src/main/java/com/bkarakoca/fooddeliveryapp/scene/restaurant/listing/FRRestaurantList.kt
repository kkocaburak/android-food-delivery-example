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
    lateinit var restaurantBindingAdapter: AdapterBindingRestaurantList

    override fun initialize() {
        initRecyclerView()
        viewModel.initializeVM()
    }

    private fun initRecyclerView() {
        binder.recyclerviewRestaurant.apply {
            adapter = restaurantBindingAdapter
            itemAnimator?.changeDuration = 0
        }
    }

    override fun setListeners() {
        restaurantBindingAdapter.apply {
            setOnRestaurantClickListener { restaurantUIModel ->
                viewModel.onRestaurantClicked(restaurantUIModel)
            }

            setOnRestaurantFavoriteClickListener { restaurantUIModel ->
                viewModel.handleFavoriteRestaurant(restaurantUIModel)
            }
        }
    }

    override fun setReceivers() {
        observe(viewModel.restaurantListUIModel) { restaurantList ->
            restaurantList?.let {
                restaurantBindingAdapter.submitList(ArrayList(it))
            }
        }
    }
}