package com.example.whitelabelexample.common.delegates

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

open class SimpleDelegatesAdapter(delegates: SparseArrayCompat<SimpleAdapterDelegate>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<BaseDelegatesAdapterItem>()
    private val delegatesManager: AdapterDelegatesManager<List<BaseDelegatesAdapterItem>> = AdapterDelegatesManager()

    init {
        addDelegates(delegates)
    }

    private fun addDelegates(delegates: SparseArrayCompat<SimpleAdapterDelegate>){
        for (i in 0..delegates.size()) {
            delegates[i]?.let {
                val delegateType = delegates.keyAt(i)
                delegatesManager.addDelegate(delegateType, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegatesManager.onCreateViewHolder(parent, viewType)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegatesManager.onBindViewHolder(items, position, holder)

    override fun getItemViewType(position: Int) = items[position].adapterType.type

    fun swapItems(items: List<BaseDelegatesAdapterItem>) {
        if (this.items != items) {
            this.items = items
            notifyDataSetChanged()
        }
    }
}