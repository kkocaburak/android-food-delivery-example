package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.domain.restaurant.GetRestaurantListUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.HandleRestaurantFavoriteUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.SortRestaurantsUseCase
import com.bkarakoca.fooddeliveryapp.internal.extension.launch
import com.bkarakoca.fooddeliveryapp.internal.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    val restaurantListUIModel = MutableLiveData<RestaurantListUIModel>()
    private val sortingType = MutableLiveData(RestaurantSortingType.BEST_MATCH)

    fun initializeVM() {
        fetchRestaurantList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun fetchRestaurantList() = launch {
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
                    onRestaurantSortClicked(
                        true,
                        sortingType.value ?: RestaurantSortingType.BEST_MATCH
                    )
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
                handleFailure(Failure.CustomException(e.message))
            }.onStart {
                // TODO : showLoading
            }.onCompletion {
                // TODO : hideLoading
            }.collect {
                fetchRestaurantList()
            }
        }
    }

    fun onRestaurantSortClicked(isChecked: Boolean, _sortingType: RestaurantSortingType) = launch {
        if (isChecked) {
            sortingType.value = _sortingType

            restaurantListUIModel.value?.let { _restaurantListUIModel ->
                sortRestaurantsUseCase.execute(
                    SortRestaurantsUseCase.Params(_restaurantListUIModel, _sortingType)
                ).collect {
                    filteredRestaurantListUIModel.value = (it.copy())
                }
            } ?: handleNullRestaurantList()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun handleNullRestaurantList() {
        handleFailure(Failure.CustomException("Null Restaurant List"))
    }

}