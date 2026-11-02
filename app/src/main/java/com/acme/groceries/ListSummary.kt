package com.acme.groceries

/** Builds the line shown at the top of the list, e.g. "2 of 3 left · last added Eggs". */
object ListSummary {

    const val EMPTY_HEADLINE = "Your list is empty · add an item above"

    fun headline(items: List<Item>): String {
        val newest = items.maxByOrNull { it.addedAt } ?: return EMPTY_HEADLINE
        val left = items.count { !it.done }
        return "$left of ${items.size} left · last added ${newest.name}"
    }
}
