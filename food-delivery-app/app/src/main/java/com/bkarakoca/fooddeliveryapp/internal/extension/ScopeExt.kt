package com.bkarakoca.fooddeliveryapp.internal.extension

inline fun <T: Any, R : Any> safeWith(value: T?, block: T.() -> R) : R? {
    return if (value != null) {
        block(value)
    } else {
        null
    }
}