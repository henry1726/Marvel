package com.example.marvel.data.data_source

import com.example.marvel.data.data_source.dto.CharactersDTO.CharactersDTO
import com.example.marvel.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String =  Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset")offset:String
    ): Response<CharactersDTO>

   /* @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId")characterId:String,
        @Query("ts")ts:String= Constants.timeStamp,
        @Query("apikey")apikey:String=Constants.API_KEY,
        @Query("hash")hash:String=Constants.hash(),
    ): CharacterDTO*/
}