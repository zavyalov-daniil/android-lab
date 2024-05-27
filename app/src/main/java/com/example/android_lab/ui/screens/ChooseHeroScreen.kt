package com.example.android_lab.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.android_lab.ui.components.HeroList
import com.example.android_lab.model.HeroModel
import com.example.android_lab.ui.components.MarvelHeader

@Composable
fun ChooseHeroScreen(heroes: List<HeroModel>, onPreviewClick: (HeroModel) -> Unit = {}) {
    val backColor = remember {
        mutableStateOf(Color.Black)
    }

    Column {
        MarvelHeader(modifier = Modifier.align(Alignment.CenterHorizontally))
        HeroList(
            heroes = heroes,
            onClick = onPreviewClick,
            onScroll = { color ->
                backColor.value = color
            }
        )
    }
}