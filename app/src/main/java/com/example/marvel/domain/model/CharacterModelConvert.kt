package com.example.marvel.domain.model

import java.io.Serializable

data class CharacterModelConvert(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt: String,
    val comics : List<String>
) : Serializable {

}
