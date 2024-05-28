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
import com.example.android_lab.repository.MarvelApi
import com.example.android_lab.repository.MarvelApiConstants
import com.example.android_lab.repository.MarvelApiRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : ComponentActivity() {
    private val heroes = listOf<HeroModel>(
//        HeroModel(
//            "Deadpool",
//            "Please don’t make the super suit green...or animated!",
//            "https://iili.io/JMnAfIV.png"
//        ),
//        HeroModel(
//            "Iron men",
//            "Sometimes you have to run, even before you learn to walk.",
//            "https://iili.io/JMnuDI2.png"
//        ),
//        HeroModel(
//            "Spider Man",
//            "In iron suit",
//            "https://iili.io/JMnuyB9.png"
//        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()
            val heroList = remember {
                mutableListOf<HeroModel>()
            }
            val currentHero = remember {
                mutableStateOf(heroes[0])
            }

            LaunchedEffect(key1 = Unit) {
                CoroutineScope(Dispatchers.IO).launch {
                    val moshi = Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                    val api = Retrofit.Builder()
                        .baseUrl(MarvelApiConstants.BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()
                        .create(MarvelApi::class.java)

                    val marvelApiRepository = MarvelApiRepository(marvelApi = api)

                    val allHeroes = marvelApiRepository.findAll()
                    val hero = marvelApiRepository.findById(allHeroes.data!!.results[0].id.toString())

                    if (hero.code == 200)
                        with(hero.data?.results?.get(0)) {
                            currentHero.value = HeroModel(
                                id = this?.id,
                                name = this?.name ?: "",
                                desc = this?.description ?: "",
                                img = "${
                                    this?.thumbnail?.path?.replace(
                                        oldValue = "http",
                                        newValue = "https"
                                    )
                                }.${this?.thumbnail?.extension}"
                            )
                        }

                    if (allHeroes.code == 200) {
                        heroList.clear()
                        with(allHeroes.data!!.results) {
                            for (i in this.indices) {
                                val heroDto: HeroDto = this[i]
                                val heroModel = HeroModel(
                                    id = heroDto.id,
                                    name = heroDto.name ?: "",
                                    desc = heroDto.description ?: "",
                                    img = "${
                                        heroDto.thumbnail?.path?.replace(
                                            oldValue = "http",
                                            newValue = "https"
                                        )
                                    }.${heroDto.thumbnail?.extension}"
                                )
                                heroList.add(heroModel)
                            }
                        }
                    }
                }
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
