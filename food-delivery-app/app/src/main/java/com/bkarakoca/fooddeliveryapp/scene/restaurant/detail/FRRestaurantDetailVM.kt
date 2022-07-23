package com.bkarakoca.fooddeliveryapp.scene.restaurant.detail

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.fooddeliveryapp.base.BaseViewModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FRRestaurantDetailVM @Inject constructor() : BaseViewModel() {

    val restaurantUIModel = MutableLiveData<RestaurantUIModel>()

    fun initializeVM(args: FRRestaurantDetailArgs) {
        restaurantUIModel.value = args.restaurantUIModel
    }

}