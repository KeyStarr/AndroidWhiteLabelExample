package com.example.whitelabelexample.ui.loyalty.getcard.recycler.delegates

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.whitelabelexample.common.delegates.AdapterDelegateType
import com.example.whitelabelexample.common.delegates.SimpleAdapterDelegate
import com.example.whitelabelexample.ui.loyalty.getcard.model.FieldItem

abstract class BaseFieldAdapterDelegate(adapterDelegateType: AdapterDelegateType) : SimpleAdapterDelegate(adapterDelegateType)

abstract class BaseFieldViewHolder(
    itemView: View,
    protected val onFieldInputChanged: (FieldItem) -> Unit
) : RecyclerView.ViewHolder(itemView)
