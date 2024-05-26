package com.example.marvel.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marvel.R

@Composable
fun ChooseYourHeroScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = modifier)
        Column(
            modifier = modifier.padding(top = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "There must be poster",
                modifier = modifier
            )
            Text(
                text = "Choose your hero",
                modifier = modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = modifier)
    }


}

@Preview(showSystemUi = true, backgroundColor = 0x1f000F0F, showBackground = true)
@Composable
fun PreviewChooseYourHeroScreen() {
    ChooseYourHeroScreen()
}
