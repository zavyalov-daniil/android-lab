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
import androidx.compose.runtime.LaunchedEffect
import com.example.android_lab.dto.HeroDto
import com.example.android_lab.mapper.toModel
import com.example.android_lab.repository.MarvelApi
import com.example.android_lab.repository.MarvelApiConstants
import com.example.android_lab.repository.MarvelApiRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val heroPlaceHolder = HeroModel(
                0,
                "Deadpool",
                "Please don’t make the super suit green...or animated!",
                "https://iili.io/JMnAfIV.png"
            )
            val heroList = remember {
                mutableListOf<HeroModel>(heroPlaceHolder)
            }
            val currentHero = remember {
                mutableStateOf(heroList[0])
            }
            val navigationController = rememberNavController()
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val api = Retrofit.Builder()
                .baseUrl(MarvelApiConstants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(MarvelApi::class.java)
            val marvelApiRepository = MarvelApiRepository(marvelApi = api)

            runBlocking {
                val allHeroes = marvelApiRepository.findAll()
                val hero = marvelApiRepository.findById(allHeroes.data!!.results[0].id.toString())
                var isError = false

                if (hero.code == 200) {
                    with(hero.data?.results?.get(0)) {
                        currentHero.value = this.toModel()
                    }
                } else {
                    isError = true
                }

                if (allHeroes.code == 200) {
                    heroList.clear()
                    with(allHeroes.data!!.results) {
                        for (i in this.indices) {
                            val heroDto: HeroDto = this[i]
                            val heroModel = heroDto.toModel()
                            heroList.add(heroModel)
                        }
                    }
                } else {
                    isError = true
                }
            }

            AndroidlabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlackBackground
                ) {
                    ScreensContent(heroList, navigationController, currentHero)
                }
            }
        }
    }
}

@Composable
private fun ScreensContent(
    heroes: MutableList<HeroModel>,
    navigationController: NavHostController,
    selectedHero: MutableState<HeroModel>,
    isError: Boolean = false
) {
    NavHost(navController = navigationController, startDestination = Screen.HERO_LIST.name) {
        composable(route = Screen.HERO_LIST.name) {
            if (isError) {

            } else {
                ChooseHeroScreen(
                    heroes = heroes,
                    onPreviewClick = { hero ->
                        selectedHero.value = hero
                        navigationController.navigate(route = Screen.HERO_INFO.name)
                    })
            }
        }
        composable(route = Screen.HERO_INFO.name) {
            HeroScreen(hero = selectedHero.value, onReturnCLick = {
                navigationController.popBackStack()
            })
        }
    }
}
