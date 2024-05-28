package com.example.android_lab.dto

import com.squareup.moshi.Json

data class HeroDto(
    @Json(name = "id") var id: Int? = null,
    @Json(name = "name") var name: String? = null,
    @Json(name = "description") var description: String? = null,
    @Json(name = "modified") var modified: String? = null,
    @Json(name = "thumbnail") var thumbnail: HeroThumbnailDto? = HeroThumbnailDto(),
    @Json(name = "resourceURI") var resourceURI: String? = null,
)
