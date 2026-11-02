package com.acme.groceries

import android.content.SharedPreferences

/** Saves the list on the device. A fresh install starts with a few sample items. */
class ItemStore(private val prefs: SharedPreferences) {

    fun load(): List<Item> {
        val saved = prefs.getString(KEY_ITEMS, null) ?: return SAMPLE_ITEMS
        return ItemCodec.decode(saved)
    }

    fun save(items: List<Item>) {
        prefs.edit().putString(KEY_ITEMS, ItemCodec.encode(items)).apply()
    }

    companion object {
        private const val KEY_ITEMS = "items"

        val SAMPLE_ITEMS = listOf(
            Item(name = "Milk", addedAt = 1_730_000_000_000),
            Item(name = "Bread", addedAt = 1_730_000_060_000),
            Item(name = "Eggs", addedAt = 1_730_000_120_000),
        )
    }
}
