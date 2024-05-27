package com.example.android_lab.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.android_lab.R
import com.example.android_lab.model.HeroModel
import com.example.android_lab.ui.theme.Spaces
import com.example.android_lab.ui.theme.SecondaryWhite
import com.example.android_lab.ui.theme.Styles

@Composable
fun HeroScreen(hero: HeroModel, onReturnCLick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = hero.img,
            contentDescription = stringResource(R.string.placeholder_img)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopStart)
        ) {
            Spacer(modifier = Modifier.height(Spaces.Heroes.heroPageSpacer))
            IconButton(
                onClick = onReturnCLick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = SecondaryWhite
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.placeholder_img)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = Spaces.Heroes.heroPageSpace,
                alignment = Alignment.Bottom
            ),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Spaces.Screens.allPaddingScreen)
                .align(Alignment.BottomStart)
        ) {

            Text(
                text = hero.name,
                style = Styles.mainText,
                textAlign = TextAlign.Start
            )
            Text(
                text = hero.desc,
                style = Styles.secondaryText,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(Spaces.Heroes.heroPageSpacer))

        }
    }
}