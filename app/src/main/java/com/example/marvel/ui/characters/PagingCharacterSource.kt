package com.example.marvel.ui.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvel.data.data_source.MarvelApi
import com.example.marvel.domain.model.CharacterModel
import com.example.marvel.domain.use_cases.CharactersPagingUseCase
import java.util.HashMap

class PagingCharacterSource(
    private val charactersPagingUseCase: CharactersPagingUseCase
) : PagingSource<Int, CharacterModel>(){
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val currentPage = params.key ?: 0
            val parameters = HashMap<String, String>()
            parameters["page"] = currentPage.toString()
            val response  = charactersPagingUseCase.getAllCharacters(offset = currentPage)
            val responseData = mutableListOf<CharacterModel>()
            val data = response.body()?.data?.results ?: emptyList()
            responseData.addAll(data.map { it.toCharacter() })

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 0) null else -1,
                nextKey = currentPage.plus(20)
            )

        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}