package com.bkarakoca.fooddeliveryapp.internal.popup

class PopupListener(
    val onPositiveButtonClick: (() -> Unit)? = null,
    val onNegativeButtonClick: (() -> Unit)? = null
)
