package com.bkarakoca.fooddeliveryapp.base

interface ListAdapterItem {
    val id: String?

    override fun equals(other: Any?): Boolean
}
