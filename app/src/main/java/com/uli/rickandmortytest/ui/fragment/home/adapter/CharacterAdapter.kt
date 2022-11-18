package com.uli.rickandmortytest.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uli.rickandmortytest.databinding.ItemCharacterBinding
import com.uli.rickandmortytest.domain.entity.Result

class CharacterAdapter(private val listener: OpenDetailListener) :
    PagingDataAdapter<Result, CharacterAdapter.CharacterViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
        holder.itemView.setOnClickListener {
            model?.id?.let { it1 -> listener.openDetailCharacter(it1) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Result?) {
            binding.characterResult = model
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    interface OpenDetailListener {
        fun openDetailCharacter(id: Int)
    }
}