package com.example.whitelabelexample.common.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whitelabelexample.common.delegates.AdapterDelegateType
import com.example.whitelabelexample.common.delegates.BaseDelegatesAdapterItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

abstract class SimpleAdapterDelegate(private val adapterDelegateType: AdapterDelegateType)
    : AdapterDelegate<List<BaseDelegatesAdapterItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                adapterDelegateType.viewHolderLayoutRes, parent, false)
        return createViewHolder(view)
    }

    abstract fun createViewHolder(view: View): RecyclerView.ViewHolder

    override fun isForViewType(items: List<BaseDelegatesAdapterItem>, position: Int): Boolean =
            items[position].adapterType.type == adapterDelegateType.type

    override fun onBindViewHolder(items: List<BaseDelegatesAdapterItem>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        bindViewHolder(items, position, holder, payloads)
    }

    abstract fun bindViewHolder(items: List<BaseDelegatesAdapterItem>, position: Int,
                                holder: RecyclerView.ViewHolder, payloads: MutableList<Any>)
}