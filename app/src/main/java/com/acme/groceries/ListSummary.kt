package com.acme.groceries

/** Builds the line shown at the top of the list, e.g. "2 of 3 left · last added Eggs". */
object ListSummary {

    fun headline(items: List<Item>): String {
        val left = items.count { !it.done }
        val newest = items.maxBy { it.addedAt }
        return "$left of ${items.size} left · last added ${newest.name}"
    }
}
