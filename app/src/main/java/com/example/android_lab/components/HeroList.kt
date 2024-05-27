package com.example.android_lab.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.android_lab.model.HeroModel

@Composable
fun HeroList(modifier: Modifier = Modifier) {
    val heroes = listOf(
        HeroModel(
            "Arseniy",
            "BASE",
            "https://iili.io/JMnuDI2.png"
        ),
        HeroModel(
            "Arseniy",
            "BASE",
            "https://iili.io/JMnuDI2.png"
        )
    )
    LazyRow(modifier = modifier) {
        items(heroes) { hero ->
            HeroPreview(hero, modifier)
        }
    }
}
