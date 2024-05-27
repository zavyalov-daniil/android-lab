package com.example.android_lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.android_lab.ui.screens.ChooseHeroScreen
import com.example.android_lab.model.HeroModel
import com.example.android_lab.ui.Screen
import com.example.android_lab.ui.theme.AndroidlabTheme
import com.example.android_lab.ui.theme.BlackBackground
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_lab.ui.screens.HeroScreen

class MainActivity : ComponentActivity() {
    private val heroes = listOf(
        HeroModel(
            "Deadpool",
            "Please don’t make the super suit green...or animated!",
            "https://iili.io/JMnAfIV.png"
        ),
        HeroModel(
            "Iron men",
            "Sometimes you have to run, even before you learn to walk.",
            "https://iili.io/JMnuDI2.png"
        ),
        HeroModel(
            "Spider Man",
            "In iron suit",
            "https://iili.io/JMnuyB9.png"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()
            val currentHero = remember {
                mutableStateOf(heroes[0])
            }

            AndroidlabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlackBackground
                ) {
                    ScreensContent(heroes, navigationController, currentHero)
                }
            }
        }
    }
}

@Composable
private fun ScreensContent(
    heroes: List<HeroModel>,
    navigationController: NavHostController,
    selectedHero: MutableState<HeroModel>
) {
    NavHost(navController = navigationController, startDestination = Screen.HERO_LIST.name) {
        composable(route = Screen.HERO_LIST.name) {
            ChooseHeroScreen(
                heroes = heroes,
                onPreviewClick = { hero ->
                    selectedHero.value = hero
                    navigationController.navigate(route = Screen.HERO_INFO.name)
                })
        }
        composable(route = Screen.HERO_INFO.name) {
            HeroScreen(hero = selectedHero.value, onReturnCLick = {
                navigationController.popBackStack()
            })
        }
    }
}
