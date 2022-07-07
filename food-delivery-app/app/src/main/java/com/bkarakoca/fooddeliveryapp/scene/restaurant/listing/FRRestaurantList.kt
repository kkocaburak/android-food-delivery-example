package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseFragment
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType
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
        }
    }

    override fun setListeners() {
        restaurantBindingAdapter.apply {
            setOnRestaurantClickListener { restaurantUIModel ->
                viewModel.onRestaurantClicked(restaurantUIModel)
            }

            setOnRestaurantFavoriteClickListener { restaurantUIModel ->
                viewModel.onRestaurantFavoriteClicked(restaurantUIModel)
            }
        }

        binder.apply {
            chipBestMatch.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.BEST_MATCH
                )
            }
            chipNewest.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.NEWEST
                )
            }
            chipRating.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.RATING
                )
            }
            chipDistance.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.DISTANCE
                )
            }
            chipPopularity.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.POPULARITY
                )
            }
            chipAvgProductPrice.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.AVERAGE_PRODUCT_PRICE
                )
            }
            chipDeliveryCost.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.DELIVERY_COST
                )
            }
            chipMinimumCost.setOnCheckedChangeListener { _, isChecked ->
                this@FRRestaurantList.viewModel.onRestaurantSortClicked(
                    isChecked,
                    RestaurantSortingType.MIN_COST
                )
            }
        }
    }

    override fun setReceivers() {
        observe(viewModel.filteredRestaurantListUIModel) { restaurantList ->
            restaurantList?.let {
                binder.recyclerviewRestaurant.adapter = restaurantBindingAdapter.apply {
                    submitList(null)
                    submitList(it.restaurantItemList)
                }
            }
        }
    }
}