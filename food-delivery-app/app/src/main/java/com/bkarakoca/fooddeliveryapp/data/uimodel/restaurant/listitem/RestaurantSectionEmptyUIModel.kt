package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem

import android.os.Parcelable
import androidx.annotation.LayoutRes
import com.bkarakoca.fooddeliveryapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantSectionEmptyUIModel(
    override var id: Long? = null,
    @LayoutRes override val layoutId: Int = R.layout.item_restaurant_section_empty,
    val emptySectionText: String
) : RestaurantListItemType, Parcelable
