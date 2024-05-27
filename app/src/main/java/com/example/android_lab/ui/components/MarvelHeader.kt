package com.example.android_lab.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_lab.R
import com.example.android_lab.ui.theme.SecondaryWhite
import com.example.android_lab.ui.theme.Spaces
import com.example.android_lab.ui.theme.Styles

@Composable
fun MarvelHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = stringResource(R.string.marvel_logo),
            modifier = Modifier
                .padding(
                    top = Spaces.Header.headerLogoTopPadding,
                    bottom = Spaces.Header.headerLogoBottomPadding
                )
                .height(Spaces.Header.headerLogoHeight)
                .align(Alignment.CenterHorizontally),
            contentDescription = stringResource(R.string.placeholder_img)
        )
        Text(
            text = stringResource(id = R.string.choose_hero),
            style = Styles.headerText,
            color = SecondaryWhite,
            modifier = Modifier
                .padding(bottom = Spaces.Header.headerTextBottomPadding)
                .align(Alignment.CenterHorizontally)
        )
    }
}
