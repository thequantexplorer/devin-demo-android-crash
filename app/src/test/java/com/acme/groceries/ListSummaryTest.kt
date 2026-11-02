package com.acme.groceries

import org.junit.Assert.assertEquals
import org.junit.Test

class ListSummaryTest {

    @Test
    fun countsItemsLeftAndNamesTheNewest() {
        val items = listOf(
            Item(name = "Milk", done = true, addedAt = 1),
            Item(name = "Eggs", addedAt = 3),
            Item(name = "Bread", addedAt = 2),
        )

        assertEquals("2 of 3 left · last added Eggs", ListSummary.headline(items))
    }

    @Test
    fun allItemsDone() {
        val items = listOf(
            Item(name = "Milk", done = true, addedAt = 1),
            Item(name = "Bread", done = true, addedAt = 2),
        )

        assertEquals("0 of 2 left · last added Bread", ListSummary.headline(items))
    }
}
