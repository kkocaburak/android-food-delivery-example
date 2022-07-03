package com.bkarakoca.fooddeliveryapp.navigation

import androidx.navigation.NavDirections
import com.bkarakoca.fooddeliveryapp.internal.popup.PopupListener
import com.bkarakoca.fooddeliveryapp.internal.popup.PopupModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class Popup(val model: PopupModel, val listener: PopupListener? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
}
