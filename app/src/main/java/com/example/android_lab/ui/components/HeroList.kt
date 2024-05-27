package com.example.android_lab.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.android_lab.model.HeroModel
import com.example.android_lab.ui.theme.BlackBackground
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.android_lab.R
import com.example.android_lab.ui.theme.BlackBackground2
import com.example.android_lab.ui.theme.BlackBackground3
import com.example.android_lab.ui.theme.Sizes
import com.example.android_lab.ui.theme.Spaces
import com.example.android_lab.ui.theme.Styles

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroList(
    heroes: List<HeroModel>,
    modifier: Modifier = Modifier,
    onScroll: (Color) -> Unit = {},
    onClick: (HeroModel) -> Unit = {},
) {

    val state = rememberLazyListState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val colors = listOf(BlackBackground, BlackBackground2, BlackBackground3)
                onScroll(colors[(0..2).random()])
                return super.onPreScroll(available, source)
            }
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .nestedScroll(connection = nestedScrollConnection)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    ) {
        itemsIndexed(heroes) { index, item ->
            Spacer(modifier = Modifier.width(width = Spaces.Heroes.heroPreviewSpace))
            Button(
                shape = MaterialTheme.shapes.medium,
                contentPadding = PaddingValues(all = Sizes.HeroButton.allPadding),
                modifier = Modifier
                    .width(width = Sizes.HeroButton.width)
                    .height(height = Sizes.HeroButton.height),
                onClick = {
                    onClick(item)
                }
            ) {
                Box(modifier = Modifier.wrapContentSize()) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop,
                        model = item.img,
                        contentDescription = stringResource(R.string.placeholder_img)
                    )

                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(alignment = Alignment.BottomStart)
                            .padding(all = Spaces.Heroes.heroNamePadding),
                        text = item.name,
                        style = Styles.secondaryText
                    )
                }
            }
            Spacer(modifier = Modifier.width(width = Spaces.Heroes.heroPreviewSpace))
        }
    }
}
