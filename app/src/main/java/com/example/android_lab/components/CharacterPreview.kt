package com.example.android_lab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.marvel.model.CharacterModel
import com.example.marvel.R

@Composable
fun DrawCharacterPreview(modifier: Modifier = Modifier, model: CharacterModel) {
    Box(modifier = modifier) {
        Image(
            painter = model.img,
            contentDescription = stringResource(R.string.character_image_placeholder)
        )

    }
}

@Preview(showSystemUi = true, backgroundColor = 0x1f000F0F, showBackground = true)
@Composable
fun PreviewCharacterPreview() {
    val model = CharacterModel(
        "TestMan", "Testers gonna test", rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://iili.io/JMnuDI2.png")
                .crossfade(true)
                .build()
        )
    )
    DrawCharacterPreview(Modifier, model)
}