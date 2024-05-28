package com.example.android_lab.repository;

import androidx.annotation.Keep;

import com.example.android_lab.dto.MarvelResponseDto;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Keep
public interface MarvelApi {
    @GET("/v1/public/characters/{characterId}")
    suspend fun findById(
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String = MarvelApiConstants.PUBLIC_API_KEY,
        @Query("ts") ts: String = MarvelApiConstants.TS,
        @Query("hash") hash: String = MarvelApiConstants.createHash()
    ): MarvelResponseDto

    @GET("/v1/public/characters")
    suspend fun findAll(
            @Query("limit") limit: String = MarvelApiConstants.MAX_RESULT,
            @Query("apikey") apiKey: String = MarvelApiConstants.PUBLIC_API_KEY,
            @Query("ts") ts: String = MarvelApiConstants.TS,
            @Query("hash") hash: String = MarvelApiConstants.createHash(),
    ): MarvelResponseDto
}
