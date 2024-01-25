package com.example.marvel.ui.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.databinding.ItemCharacterBinding
import com.example.marvel.domain.model.CharacterModel
import com.example.marvel.ui.characters.PagingCharactersAdapter.PagingViewHolder

class PagingCharactersAdapter(private val context: Context,
                              private val listener: (CharacterModel?, ActionListener) -> Unit) :
    PagingDataAdapter<CharacterModel, PagingViewHolder>(diffCallback){

    inner class PagingViewHolder(
        private val binding: ItemCharacterBinding,
        private val context: Context,
        private val listener: (CharacterModel?, ActionListener) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : CharacterModel?){
            data?.let { character->
                with(binding){
                    val imageUrl = "${character.thumbnail.replace("http", "https")}/portrait_xlarge.${character.thumbnailExt}"
                    Glide.with(context)
                        .load(imageUrl)
                        .centerCrop()
                        .into(imgCharacterImage)
                    txtCharacterName.text= character.name
                    cvCharacter.setOnClickListener {
                        listener(data, ActionListener.CLICK_CARD)
                    }
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            ItemCharacterBinding.inflate
                (LayoutInflater.from(context), parent, false), context, listener)
    }

}

enum class ActionListener {
    CLICK_CARD
}