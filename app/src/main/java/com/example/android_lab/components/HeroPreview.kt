package com.example.android_lab.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_lab.R
import com.example.android_lab.model.HeroModel

@Composable
fun HeroPreview(hero: HeroModel, modifier: Modifier = Modifier) {
    AsyncImage(
        model = hero.img,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxHeight()
            .fillMaxWidth(),
        contentDescription = stringResource(R.string.placeholder_img)
    )
}