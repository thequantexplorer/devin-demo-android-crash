package com.acme.groceries

data class Item(
    val name: String,
    val done: Boolean = false,
    val addedAt: Long,
)
