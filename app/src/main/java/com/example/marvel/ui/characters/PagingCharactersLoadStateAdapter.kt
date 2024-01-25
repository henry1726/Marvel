package com.example.marvel.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.ItemLoadingStateBinding
import com.example.marvel.ui.characters.PagingCharactersLoadStateAdapter.PagingCharactersLoadStateViewHolder

class PagingCharactersLoadStateAdapter (
    private val retry: () -> Unit
): LoadStateAdapter<PagingCharactersLoadStateViewHolder>(){

    class PagingCharactersLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(loadState: LoadState){
            if (loadState is LoadState.Error){
                binding.textViewError.text = loadState.error.localizedMessage
            }

            binding.progressbar.isVisible = loadState is LoadState.Loading
            binding.buttonRetry.isVisible = loadState is LoadState.Error
            binding.textViewError.isVisible = loadState is LoadState.Error

            binding.buttonRetry.setOnClickListener {
                retry()
            }

            binding.progressbar.visibility = View.VISIBLE
        }

    }

    override fun onBindViewHolder(holder: PagingCharactersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingCharactersLoadStateViewHolder = PagingCharactersLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false), retry
    )
}