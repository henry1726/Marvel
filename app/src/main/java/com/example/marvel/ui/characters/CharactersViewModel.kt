package com.example.marvel.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.marvel.data.data_source.MarvelApi
import com.example.marvel.data.data_source.dto.CharactersDTO.CharactersDTO
import com.example.marvel.domain.model.CharacterModel
import com.example.marvel.domain.use_cases.CharactersPagingUseCase
import com.example.marvel.domain.use_cases.CharactersUseCase
import com.example.marvel.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersPagingUseCase: CharactersPagingUseCase,
    private val charactersUseCase: CharactersUseCase
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)){
        PagingCharacterSource(charactersPagingUseCase)
    }.flow.cachedIn(viewModelScope)

    private val _responseCharacters = MutableLiveData<Resource<CharactersDTO?>>()
    val responseCharacter get() = _responseCharacters

    fun getAllCharacters(offset: Int) = viewModelScope.launch {
        charactersUseCase.getAllCharacters(offset = offset).collect{
            responseCharacter.value = it
        }
    }
}