package com.example.android_lab.mapper

import com.example.android_lab.dto.HeroDto
import com.example.android_lab.model.HeroModel

fun HeroDto?.toModel(): HeroModel {
    return HeroModel(
        id = this?.id,
        name = this?.name ?: "",
        desc = this?.description ?: "",
        img = "${
            this?.thumbnail?.path?.replace(
                oldValue = "http",
                newValue = "https"
            )
        }.${this?.thumbnail?.extension}"
    )
}