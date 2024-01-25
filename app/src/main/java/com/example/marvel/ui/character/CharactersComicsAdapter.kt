package com.example.marvel.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.ItemComicBinding
import com.example.marvel.ui.character.CharactersComicsAdapter.CharactersComicsHolder

class CharactersComicsAdapter(
    private val list: List<String>
): RecyclerView.Adapter<CharactersComicsHolder>(){

    class CharactersComicsHolder(
        private val binding: ItemComicBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: String){
            with(binding){
                txtCharacterComic.text = comic
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersComicsHolder {
        return CharactersComicsHolder(ItemComicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CharactersComicsHolder, position: Int) {
        holder.bind(list[position])
    }
}