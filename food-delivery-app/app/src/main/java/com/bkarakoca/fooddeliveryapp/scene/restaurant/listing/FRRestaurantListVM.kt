package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListItemType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.handleFavorite
import com.bkarakoca.fooddeliveryapp.domain.restaurant.GetRestaurantListUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.HandleRestaurantFavoriteUseCase
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
) : BaseViewModel() {

    val restaurantListUIModel = MutableLiveData<List<RestaurantListItemType>?>()

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
                    restaurantListUIModel.postValue(it)
                }
        }
    }

    fun onRestaurantClicked(restaurantUIModel: RestaurantUIModel) {
        navigate(FRRestaurantListDirections.toFRRestaurantDetail(restaurantUIModel))
    }

    fun handleFavoriteRestaurant(restaurantUIModel: RestaurantUIModel) = launch {
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
            }.collect{ isSuccess ->
                if (isSuccess) {
                    val newList = restaurantListUIModel.value?.apply {
                        find {
                            it.id == restaurantUIModel.id
                        }?.let {
                            if (it is RestaurantUIModel) {
                                it.handleFavorite(!restaurantUIModel.isRestaurantFavorite)
                            }
                        }
                    }
                    restaurantListUIModel.postValue(newList)
                } else {
                    // TODO : handle error
                }
            }
        }
    }

}