package com.mehmetkaya.scorpion.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

open class BindingListAdapter<T : Any, DB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<T> = defaultDiffCallback(),
    private val viewHolderBlock: ListAdapterViewHolder<T, DB>.() -> Unit
) : ListAdapter<T, ListAdapterViewHolder<T, DB>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapterViewHolder<T, DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: DB = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return ListAdapterViewHolder<T, DB>(binding).apply(viewHolderBlock)
    }

    override fun onBindViewHolder(holder: ListAdapterViewHolder<T, DB>, position: Int) {
        holder.run {
            item = currentList[position]
            onBind?.invoke(item)
        }
    }

    companion object {
        private fun <T> defaultDiffCallback() = object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem
            override fun areContentsTheSame(oldItem: T, newItem: T) = true
        }
    }
}

class ListAdapterViewHolder<T : Any, DB : ViewDataBinding>(
    val binding: DB
) : RecyclerView.ViewHolder(binding.root) {

    internal lateinit var item: T
    internal var onBind: ((item: T) -> Unit)? = null

    fun onBind(block: (item: T) -> Unit) {
        onBind = block
    }

    fun onClick(block: (item: T) -> Unit) {
        binding.root.setOnClickListener { block(item) }
    }
}
