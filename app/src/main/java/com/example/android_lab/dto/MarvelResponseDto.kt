package com.example.android_lab.dto

import com.squareup.moshi.Json

data class MarvelResponseDto(
    @Json(name = "code") var code: Int? = null,
    @Json(name = "status") var status: String? = null,
    @Json(name = "data") var data: HeroDataDto? = HeroDataDto()
) {
}