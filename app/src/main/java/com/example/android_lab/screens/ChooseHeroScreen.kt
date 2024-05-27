package com.example.android_lab.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_lab.R
import com.example.android_lab.components.HeroList
import com.example.android_lab.ui.theme.WhiteText

@Composable
fun ChooseHeroScreen() {
    Column(modifier = Modifier) {
        AsyncImage(
            model = "https://iili.io/JMnuvbp.png",
            modifier = Modifier
                .padding(
                    top = 35.dp,
                    bottom = 40.dp
                )
                .height(30.dp)
                .align(Alignment.CenterHorizontally),
            contentDescription = stringResource(R.string.placeholder_img)
        )
        Text(
            text = stringResource(id = R.string.choose_hero),
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(700),
                letterSpacing = 0.6.sp,
            ),
            color = WhiteText,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.CenterHorizontally)
        )
        HeroList()
    }
}