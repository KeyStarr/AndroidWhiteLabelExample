package com.example.whitelabelexample.ui.loyalty.getcard.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whitelabelexample.ui.loyalty.getcard.model.FieldItem
import com.example.whitelabelexample.ui.common.delegates.BaseDelegatesAdapterItem
import com.example.whitelabelexample.ui.common.delegates.SimpleAdapterDelegate
import com.example.whitelabelexample.ui.common.delegates.SimpleDelegatesAdapter
import com.example.whitelabelexample.ui.common.views.LeakSafeRecyclerView
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.FieldsAdapterTypes.*
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.delegates.ChoiceFieldDelegate
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.delegates.DateFieldDelegate
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.delegates.TextFieldDelegate

class FieldsRecyclerView(context: Context, attrs: AttributeSet?) : LeakSafeRecyclerView(context, attrs) {

    private lateinit var castedAdapter: SimpleDelegatesAdapter

    init {
        layoutManager = LinearLayoutManager(context)
    }

    fun initAdapter(onFieldInputChanged: (FieldItem) -> Unit) {
        val delegates = initDelegates(onFieldInputChanged)
        castedAdapter = SimpleDelegatesAdapter(delegates)
        adapter = castedAdapter
    }

    private fun initDelegates(onFieldInputChanged: (FieldItem) -> Unit) =
        SparseArrayCompat<SimpleAdapterDelegate>().apply {
            put(TEXT.type, TextFieldDelegate(onFieldInputChanged))
            put(CHOICE.type, ChoiceFieldDelegate(onFieldInputChanged))
            put(DATE.type, DateFieldDelegate(onFieldInputChanged))
        }

    fun swapItems(items: List<BaseDelegatesAdapterItem>) {
        castedAdapter.swapItems(items)
    }
}
