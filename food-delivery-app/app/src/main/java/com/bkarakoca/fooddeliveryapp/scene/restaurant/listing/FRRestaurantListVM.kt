package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.domain.restaurant.GetRestaurantListUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.HandleRestaurantFavoriteUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.SortRestaurantsUseCase
import com.bkarakoca.fooddeliveryapp.internal.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FRRestaurantListVM @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase,
    private val handleRestaurantFavoriteUseCase: HandleRestaurantFavoriteUseCase,
    private val sortRestaurantsUseCase: SortRestaurantsUseCase,
) : BaseViewModel() {

    val filteredRestaurantListUIModel = MutableLiveData<RestaurantListUIModel>()
    private val restaurantListUIModel = MutableLiveData<RestaurantListUIModel>()

    fun initializeVM() {
        fetchRestaurantList()
    }

    private fun fetchRestaurantList() = launch {
        withContext(Dispatchers.IO) {
            getRestaurantListUseCase.execute(Unit)
                .onStart {
                    // TODO : showLoading
                }
                .onCompletion {
                    // TODO : hideLoading
                }
                .collect {
                    restaurantListUIModel.postValue(it.copy())
                    filteredRestaurantListUIModel.postValue(it.copy())
                }
        }
    }

    fun onRestaurantClicked(restaurantUIModel: RestaurantUIModel) {
        navigate(FRRestaurantListDirections.toFRRestaurantDetail(restaurantUIModel))
    }

    fun onRestaurantFavoriteClicked(restaurantUIModel: RestaurantUIModel) = launch {
        withContext(Dispatchers.IO) {
            handleRestaurantFavoriteUseCase.execute(
                HandleRestaurantFavoriteUseCase.Params(restaurantUIModel)
            ).catch { e ->
                println(e.localizedMessage)
            }.onStart {
                // TODO : showLoading
                delay(500)
            }.onCompletion {
                // TODO : hideLoading
            }.collect {
                fetchRestaurantList()
            }
        }
    }

    fun onRestaurantSortClicked(isChecked: Boolean, sortingType: RestaurantSortingType) = launch {
        if (isChecked) {
            restaurantListUIModel.value?.let { _restaurantListUIModel ->
                sortRestaurantsUseCase.execute(
                    SortRestaurantsUseCase.Params(_restaurantListUIModel, sortingType)
                ).collect {
                    filteredRestaurantListUIModel.postValue(it.copy())
                }
            } ?: handleNullRestaurantList()
        }
    }

    private fun handleNullRestaurantList() {
        // TODO : show dialog
    }

}