package com.example.marvel.data.repository

import com.example.marvel.data.data_source.MarvelApi
import com.example.marvel.data.data_source.dto.CharactersDTO.CharactersDTO
import com.example.marvel.domain.repository.MarvelRepository
import com.example.marvel.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi
): MarvelRepository{
    override fun getAllCharacters(offset: Int): Flow<Resource<CharactersDTO?>> = flow{
        val result = api.getAllCharacters(offset = offset.toString()).run {
            if (isSuccessful)
                Resource.success(body())
            else
                Resource.error(errorBody().toString())
        }
        emit(result)
    }

    suspend fun getAllCharactersPaging(offset: Int) = api.getAllCharacters(offset = offset.toString())

}