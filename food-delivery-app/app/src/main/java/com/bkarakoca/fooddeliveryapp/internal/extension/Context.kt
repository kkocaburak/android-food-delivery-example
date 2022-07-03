package com.bkarakoca.fooddeliveryapp.internal.extension

import android.content.Context
import android.widget.Toast
import com.bkarakoca.fooddeliveryapp.internal.popup.Popup
import com.bkarakoca.fooddeliveryapp.internal.popup.PopupListener
import com.bkarakoca.fooddeliveryapp.internal.popup.PopupModel

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Context.showPopup(model: PopupModel, listener: PopupListener? = null) {
    Popup(this, model, listener).show()
}
