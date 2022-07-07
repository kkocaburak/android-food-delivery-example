package com.bkarakoca.fooddeliveryapp.internal.extension

fun Double?.toSafeDouble(): Double {
    return this ?: 0.0
}