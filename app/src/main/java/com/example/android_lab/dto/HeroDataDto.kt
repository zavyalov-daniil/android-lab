package com.example.android_lab.dto

import com.squareup.moshi.Json

data class HeroDataDto(
    @Json(name = "results") var results: List<HeroDto> = listOf()
)
