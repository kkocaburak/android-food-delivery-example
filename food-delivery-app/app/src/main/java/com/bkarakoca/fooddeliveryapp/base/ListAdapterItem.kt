package com.bkarakoca.fooddeliveryapp.base

interface ListAdapterItem {
    val id: Long?

    override fun equals(other: Any?): Boolean
}
