package com.example.android_lab.dto

import com.squareup.moshi.Json

data class HeroThumbnailDto(
    @Json(name = "path") var path: String? = null,
    @Json(name = "extension") var extension: String? = null
) {
}