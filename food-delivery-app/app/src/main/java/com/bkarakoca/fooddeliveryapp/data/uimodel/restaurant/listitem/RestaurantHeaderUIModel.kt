package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem

import android.os.Parcelable
import androidx.annotation.LayoutRes
import com.bkarakoca.fooddeliveryapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantHeaderUIModel(
    override var id: Long? = null,
    @LayoutRes override val layoutId: Int = R.layout.item_restaurant_header,
    val header: String,
    val sectionItemSize: Int
) : RestaurantListItemType, Parcelable