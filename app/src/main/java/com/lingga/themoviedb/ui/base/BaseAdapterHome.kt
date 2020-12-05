package com.lingga.themoviedb.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapterHome<T>(
    val onClick: (T) -> Unit = {}
) : RecyclerView.Adapter<BaseAdapterHome<T>.ViewHolder>() {

    private var items: MutableList<T> = mutableListOf()

    protected abstract val getLayoutIdRes: Int

    private fun onItemClick(item: T) = onClick(item)

    fun submitList(items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    private fun getItemFrom(position: Int): T = items[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItemFrom(position)
        holder.apply {
            itemView.setOnClickListener { onItemClick(item) }
            bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdRes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int = if (items.size > 6) 6 else items.size

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }
}
