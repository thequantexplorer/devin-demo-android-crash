package com.acme.groceries

import org.junit.Assert.assertEquals
import org.junit.Test

class ItemCodecTest {

    @Test
    fun roundTripsItems() {
        val items = listOf(
            Item(name = "Milk", done = true, addedAt = 1),
            Item(name = "Peanut butter", addedAt = 2),
        )

        assertEquals(items, ItemCodec.decode(ItemCodec.encode(items)))
    }

    @Test
    fun emptyTextIsAnEmptyList() {
        assertEquals(emptyList<Item>(), ItemCodec.decode(""))
    }

    @Test
    fun tabsInNamesDoNotBreakTheFormat() {
        val items = listOf(Item(name = "Salt\tpepper", addedAt = 5))

        assertEquals("Salt pepper", ItemCodec.decode(ItemCodec.encode(items)).single().name)
    }
}
