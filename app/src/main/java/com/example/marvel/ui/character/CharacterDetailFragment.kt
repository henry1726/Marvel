package com.example.marvel.ui.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.databinding.FragmentCharacterDetailBinding
import com.example.marvel.domain.model.CharacterModel
import com.example.marvel.domain.model.CharacterModelConvert
import com.example.marvel.utils.Constants
import com.example.marvel.utils.toObject
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val binding: FragmentCharacterDetailBinding by lazy {
        FragmentCharacterDetailBinding.inflate(layoutInflater)
    }
    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var adapter: CharactersComicsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        val model = args.model
        with(binding){
            val imageUrl = "${model.thumbnail.replace(Constants.REPLACE_OLD_STRING, Constants.REPLACE_NEW_STRING)}/portrait_xlarge.${model.thumbnailExt}"
            Glide.with(requireContext())
                .load(imageUrl)
                .centerCrop()
                .into(imgCharacterImageDetail)
            txtCharacterNameDetail.text = model.name
            descriptionCharacterDetail.text = model.description.ifEmpty { getString(R.string.no_description) }
            characterRecyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
            adapter = CharactersComicsAdapter(model.comics.orEmpty())
            characterRecyclerViewDetail.adapter = adapter
        }
    }

}