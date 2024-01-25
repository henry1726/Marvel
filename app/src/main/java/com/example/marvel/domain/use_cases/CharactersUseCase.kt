package com.example.marvel.domain.use_cases

import com.example.marvel.data.data_source.dto.CharactersDTO.CharactersDTO
import com.example.marvel.domain.model.CharacterModel
import com.example.marvel.domain.repository.MarvelRepository
import com.example.marvel.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
){
    fun getAllCharacters(offset: Int) : Flow<Resource<CharactersDTO?>> = repository.getAllCharacters(offset = offset)
}