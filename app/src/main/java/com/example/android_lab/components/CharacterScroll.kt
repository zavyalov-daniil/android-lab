package com.example.marvel.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DrawCharacterScroll(modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier) {

    }
}


@Preview(showSystemUi = true, backgroundColor = 0x1f000F0F, showBackground = true)
@Composable
fun PreviewCharacterScroll() {
    DrawCharacterScroll()
}