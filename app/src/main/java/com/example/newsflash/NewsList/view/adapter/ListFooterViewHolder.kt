package com.example.newsflash.NewsList.view.adapter

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsflash.R
import com.example.newsflash.NewsList.data.State
import com.example.newsflash.databinding.ItemListFooterBinding

class ListFooterViewHolder(val binding: ItemListFooterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(status: State?) {
       binding.progressBar.visibility = if (status == State.LOADING) VISIBLE else INVISIBLE
        binding.txtError.visibility = if (status == State.ERROR) VISIBLE else INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemListFooterBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_list_footer,
                parent, false)

            binding.txtError.setOnClickListener { retry() }
            return ListFooterViewHolder(binding)
        }
    }
}