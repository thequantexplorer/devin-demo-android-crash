package com.acme.groceries

/** Turns the list into one line per item ("done<TAB>addedAt<TAB>name") so it fits in SharedPreferences. */
object ItemCodec {

    fun encode(items: List<Item>): String =
        items.joinToString("\n") { item ->
            listOf(if (item.done) "1" else "0", item.addedAt.toString(), item.name.replace('\t', ' ').replace('\n', ' '))
                .joinToString("\t")
        }

    fun decode(text: String): List<Item> =
        text.lineSequence()
            .filter { it.isNotBlank() }
            .map { line ->
                val (done, addedAt, name) = line.split('\t', limit = 3)
                Item(name = name, done = done == "1", addedAt = addedAt.toLong())
            }
            .toList()
}
