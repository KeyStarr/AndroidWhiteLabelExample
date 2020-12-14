package com.example.whitelabelexample.common.delegates

import androidx.collection.SparseArrayCompat
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class SimpleAsyncDiffDelegatesAdapter(delegates: SparseArrayCompat<SimpleAdapterDelegate>) :
    AsyncListDifferDelegationAdapter<BaseDelegatesAdapterItem>(DefaultDiffUtilCallback(), AdapterDelegatesManager()) {

    init {
        addDelegates(delegates)
    }

    private fun addDelegates(delegates: SparseArrayCompat<SimpleAdapterDelegate>) {
        for (i in 0..delegates.size()) {
            delegates[i]?.let {
                val delegateType = delegates.keyAt(i)
                delegatesManager.addDelegate(delegateType, it)
            }
        }
    }
}
