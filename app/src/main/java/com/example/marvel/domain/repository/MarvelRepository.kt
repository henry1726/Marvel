package com.example.marvel.domain.repository

import com.example.marvel.data.data_source.dto.CharactersDTO.CharactersDTO
import com.example.marvel.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getAllCharacters(offset: Int): Flow<Resource<CharactersDTO?>>
}