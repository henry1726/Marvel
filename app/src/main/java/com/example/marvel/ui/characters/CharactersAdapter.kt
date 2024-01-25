package com.example.marvel.ui.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.databinding.ItemCharacterBinding
import com.example.marvel.domain.model.CharacterModel
import com.example.marvel.utils.Constants
import com.example.marvel.ui.characters.CharactersAdapter.CharactersHolder

class CharactersAdapter(
    private val list: List<CharacterModel>,
    private val context: Context,
    private val listener: (CharacterModel, ActionListener2) -> Unit
): RecyclerView.Adapter<CharactersHolder>() {

    class CharactersHolder(
        private val binding: ItemCharacterBinding,
        private val context: Context,
        private val listener: (CharacterModel, ActionListener2) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(character: CharacterModel){
            with(binding){
                val imageUrl = "${character.thumbnail.replace(Constants.REPLACE_OLD_STRING, Constants.REPLACE_NEW_STRING)}/portrait_xlarge.${character.thumbnailExt}"
                Glide.with(context)
                    .load(imageUrl)
                    .centerCrop()
                    .into(imgCharacterImage)
                txtCharacterName.text= character.name
                cvCharacter.setOnClickListener {
                    listener(character, ActionListener2.CLICK_CARD)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharactersHolder(binding, context, listener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        holder.bind(list[position])
    }

}

enum class ActionListener2 {
    CLICK_CARD
}