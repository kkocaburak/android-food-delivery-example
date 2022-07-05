package com.bkarakoca.fooddeliveryapp.scene.restaurant

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import com.bkarakoca.fooddeliveryapp.domain.restaurant.GetRestaurantListUseCase
import com.bkarakoca.fooddeliveryapp.internal.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FRRestaurantListVM @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : BaseViewModel() {

    val restaurantListUIModel = MutableLiveData<RestaurantListUIModel>()

    fun initializeVM(context: Context) {
        fetchRestaurantList(context)
    }

    private fun fetchRestaurantList(context: Context) = launch {
        getRestaurantListUseCase.execute(GetRestaurantListUseCase.Params(context))
            .onStart {
                // TODO : showLoading
            }
            .onCompletion {
                // TODO : hideLoading
            }
            .collect {
                restaurantListUIModel.value = it
            }
    }

}