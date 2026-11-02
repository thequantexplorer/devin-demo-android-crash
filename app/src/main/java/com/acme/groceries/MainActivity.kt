package com.acme.groceries

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var store: ItemStore
    private lateinit var headline: TextView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val items = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        store = ItemStore(getSharedPreferences("groceries", MODE_PRIVATE))
        items.addAll(store.load())

        headline = findViewById(R.id.headline)
        listView = findViewById(R.id.items)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, mutableListOf())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = items[position]
            items[position] = item.copy(done = !item.done)
            saveAndRender()
        }

        val input = findViewById<EditText>(R.id.new_item)
        val add = findViewById<Button>(R.id.add)
        add.setOnClickListener { addItem(input) }
        input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addItem(input)
                true
            } else {
                false
            }
        }

        findViewById<Button>(R.id.clear_checked).setOnClickListener {
            items.removeAll { it.done }
            saveAndRender()
        }

        render()
    }

    private fun addItem(input: EditText) {
        val name = input.text.toString().trim()
        if (name.isEmpty()) return
        items.add(Item(name = name, addedAt = System.currentTimeMillis()))
        input.text.clear()
        saveAndRender()
    }

    private fun saveAndRender() {
        store.save(items)
        render()
    }

    private fun render() {
        headline.text = ListSummary.headline(items)
        adapter.clear()
        adapter.addAll(items.map { it.name })
        items.forEachIndexed { index, item -> listView.setItemChecked(index, item.done) }
    }
}
