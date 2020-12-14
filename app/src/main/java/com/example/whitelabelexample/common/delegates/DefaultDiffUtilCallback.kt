package com.example.whitelabelexample.common.delegates

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.whitelabelexample.common.delegates.BaseDelegatesAdapterItem

class DefaultDiffUtilCallback : DiffUtil.ItemCallback<BaseDelegatesAdapterItem>() {
        override fun areItemsTheSame(oldItem: BaseDelegatesAdapterItem, newItem: BaseDelegatesAdapterItem) =
            oldItem.adapterType == newItem.adapterType && oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BaseDelegatesAdapterItem, newItem: BaseDelegatesAdapterItem) =
            oldItem == newItem
    }