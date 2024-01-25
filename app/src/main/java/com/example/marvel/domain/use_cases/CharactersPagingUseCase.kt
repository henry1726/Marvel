package com.example.marvel.domain.use_cases

import com.example.marvel.data.repository.MarvelRepositoryImpl
import javax.inject.Inject

class CharactersPagingUseCase @Inject constructor(
    private val repository: MarvelRepositoryImpl
) {
    suspend fun getAllCharacters(offset: Int) = repository.getAllCharactersPaging(offset = offset)
}