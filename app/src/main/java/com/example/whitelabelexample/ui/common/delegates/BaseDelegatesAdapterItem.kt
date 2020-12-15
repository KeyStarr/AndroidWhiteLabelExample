package com.example.whitelabelexample.ui.common.delegates

abstract class BaseDelegatesAdapterItem(val adapterType: AdapterDelegateType) {

    operator fun plus(item: BaseDelegatesAdapterItem): List<BaseDelegatesAdapterItem> {
        return listOf(this, item)
    }

    operator fun plus(items: List<BaseDelegatesAdapterItem>): List<BaseDelegatesAdapterItem> {
        val mutableListOf = mutableListOf<BaseDelegatesAdapterItem>()
        mutableListOf.add(this)
        mutableListOf.addAll(items)
        return mutableListOf
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseDelegatesAdapterItem

        if (adapterType != other.adapterType) return false

        return true
    }

    override fun hashCode(): Int {
        return adapterType.hashCode()
    }
}

interface AdapterDelegateType {
    val type: Int
    val viewHolderLayoutRes: Int

}