package com.example.android_lab.repository

import com.example.android_lab.dto.MarvelResponseDto

class MarvelApiRepository(
    private val marvelApi: MarvelApi
) {
    suspend fun findAll(): MarvelResponseDto {
        return marvelApi.findAll()
    }
    suspend fun findById(id: String): MarvelResponseDto {
        return marvelApi.findById(characterId = id)
    }
}