package com.fr0g.moventure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fr0g.moventure.detail.data.remote.model.Genre
import com.fr0g.moventure.detail.domain.models.Cast
import com.fr0g.moventure.detail.domain.models.Detail
import com.fr0g.moventure.detail.domain.models.Review
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.ui.MoventureTheme
import com.fr0g.moventure.ui.detail.components.DetailBodyContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sampleMovie = Detail(
                id = 83533,
                title = "Avatar: Fire and Ash",
                posterPath = "/lE9KpVwgeWHMwgwkNaeH5nEFh20.jpg",
                backdropPath = "/6qkJLRCZp9Y3ovXti5tSuhH0DpO.jpg",
                voteAverage = 8.2,
                releaseDate = "2024-07-26",
                genres = listOf(Genre(1, "Action"), Genre(2, "Comedy")),
                overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him.",
                originalLanguage = "TODO()",
                originalTitle = "TODO()",
                popularity = 54.4,
                reviews = listOf(
                    Review(
                        author = "shammahrashad",
                        content = "Theres not much of a plot and the villains weren't that great. It was a good laugh though and amazing cameos and fight scenes.",
                        createdAt = "2024-07-25T21:26:26.038Z",
                        id = "66a2c3023f13b7231f1b94eb",
                        rating = 6.0
                    ),
                    Review(
                        author = "shammahrashad",
                        content = "Theres not much of a plot and the villains weren't that great. It was a good laugh though and amazing cameos and fight scenes.",
                        createdAt = "2024-07-25T21:26:26.038Z",
                        id = "66a2c3023f13b7231f1b94eb",
                        rating = 6.0
                    ),
                    Review(
                        author = "shammahrashad",
                        content = "Theres not much of a plot and the villains weren't that great. It was a good laugh though and amazing cameos and fight scenes.",
                        createdAt = "2024-07-25T21:26:26.038Z",
                        id = "66a2c3023f13b7231f1b94eb",
                        rating = 6.0
                    ), Review(
                        author = "shammahrashad",
                        content = "Theres not much of a plot and the villains weren't that great. It was a good laugh though and amazing cameos and fight scenes.",
                        createdAt = "2024-07-25T21:26:26.038Z",
                        id = "66a2c3023f13b7231f1b94eb",
                        rating = 6.0
                    ),

                    Review(
                        author = "r96sk",
                        content = """Its story may not be the strongest, but the comedy makes 'Deadpool & Wolverine' an excellent watch!

There are some top notch gags in there, particularly to do with the recent offscreen changes for Wade Wilson's alter ego. As you'd expect with Ryan Reynolds in this role, the jokes are plentiful and there is barely any time to react to one before another appears. That can sometimes not work as well as intended, e.g. 'Deadpool 2', but here the humour is executed perfectly.

Reynolds himself is quality, it is a quintessential Ryan Reynolds as Deadpool performance. It's real neat to see Hugh Jackman back as Wolverine, he does play second fiddle to the other titular character but still more than holds his own, as you'd expect. Emma Corrin, meanwhile, enters the MCU with a great showing.

As noted at the top, I do think the plot couldn't been superior - but not in a negative way. What is portrayed is still entertaining to watch, it's just not necessarily on the same level as the comedic elements. A fun watch, all the same. Do I like it more than the 2016 original? I think so, would only be minorly so though.""",
                        createdAt = "2024-07-25T23:00:25.482Z",
                        id = "66a2d90909905455146125df",
                        rating = 9.0
                    ),
                    Review(
                        author = "Chris Sawin",
                        content = "_Deadpool & Wolverine_ is the best the MCU has been since Guardians of the Galaxy Vol. 3. It’s two hours of comic book-driven fan service and delivers entertaining surprises, nostalgic throwbacks, memorable action sequences, and worthwhile performances.\r\n\r\n**Full review:** https://bit.ly/WadeLogan",
                        createdAt = "2024-07-26T21:57:36.118Z",
                        id = "66a41bd02029ba46995219c8",
                        rating = 8.0
                    )
                ),
                runtime = "54",
                productionCountry = listOf("sfd", "safa"),
                language = listOf("sfd", "safa"),
                video = false,
                voteCount = 564,
                cast = listOf(
                    Cast(
                        character = "Wade Wilson / Deadpool / Nicepool",
                        id = 10859,
                        name = "Ryan Reynolds",
                        gender = 0,
                        profilePath = "/trzgptffGvAlAT6MEu01fz47cLW.jpg"
                    ),
                    Cast(
                        character = "Logan / Wolverine",
                        id = 6968,
                        name = "Hugh Jackman",
                        gender = 0,
                        profilePath = "/4Xujtewxqt6aU0Y81tsS9gkjizk.jpg"
                    ),
                    Cast(
                        character = "Cassandra Nova",
                        id = 2324569,
                        name = "Emma Corrin",
                        gender = 1,
                        profilePath = "/wqGOVOsHzZaHeHymIS40elGCnY0.jpg"
                    ),
                    Cast(
                        character = "Wade Wilson / Deadpool / Nicepool",
                        id = 10859,
                        name = "Ryan Reynolds",
                        gender = 0,
                        profilePath = "/trzgptffGvAlAT6MEu01fz47cLW.jpg"
                    ),
                    Cast(
                        character = "Logan / Wolverine",
                        id = 6968,
                        name = "Hugh Jackman",
                        gender = 0,
                        profilePath = "/4Xujtewxqt6aU0Y81tsS9gkjizk.jpg"
                    ),
                    Cast(
                        character = "Cassandra Nova",
                        id = 2324569,
                        name = "Emma Corrin",
                        gender = 0,
                        profilePath = "/wqGOVOsHzZaHeHymIS40elGCnY0.jpg"
                    )
                )
            )

            val movieList = listOf(
                Movie(
                    id = 680734,
                    title = "Crawlers",
                    overview = "On Saint Patrick's day—a night of wild parties and drunken revelry—three unlikely friends band together to save a college town from a vicious horde of body-snatching aliens.",
                    posterPath = "/p21gW49vIj9hjuitReZ0Xue9OJi.jpg",
                    backdropPath = "/fNDWcViPxTZZJpFcv1SacrPBWJW.jpg",
                    releaseDate = "2020-03-06",
                    voteAverage = 6.0,
                    voteCount = 82,
                    popularity = 0.6143,
                    genreIds = listOf("27", "878", "53"),
                    originalLanguage = "en",
                    originalTitle = "Crawlers",
                    adult = false,
                    video = false
                ),
                Movie(
                    id = 45887,
                    title = "Alive in Joburg",
                    overview = "A documentary-style short film about the arrival of an alien spaceship over Johannesburg, South Africa.",
                    posterPath = "/nQFaObHn4rLQ0dkGzU5T0RUVxGc.jpg",
                    backdropPath = "/aSg0K0V7inwBMpXvO7wR1Mk6gkU.jpg",
                    releaseDate = "2005-01-01",
                    voteAverage = 6.729,
                    voteCount = 107,
                    popularity = 0.2274,
                    genreIds = listOf("878"),
                    originalLanguage = "en",
                    originalTitle = "Alive in Joburg",
                    adult = false,
                    video = false
                ),
                Movie(
                    id = 314095,
                    title = "The Lost City of Z",
                    overview = "A true-life drama in the 1920s, centering on British explorer Col. Percy Fawcett, who discovered evidence of a previously unknown, advanced civilization in the Amazon and disappeared whilst searching for it.",
                    posterPath = "/dgDVkGFltTm1MCoFtlq91r7jwi9.jpg",
                    backdropPath = "/upatBMqiS0gSiNy52cwxKKcNne2.jpg",
                    releaseDate = "2017-03-15",
                    voteAverage = 6.393,
                    voteCount = 3030,
                    popularity = 2.5777,
                    genreIds = listOf("18"),
                    originalLanguage = "en",
                    originalTitle = "The Lost City of Z",
                    adult = false,
                    video = false
                ),
                Movie(
                    id = 471103,
                    title = "The Fight for Rome II - The Betrayal",
                    overview = "The devious general Cethegus plays the Byzantine and Gothic forces against each other for his own gain.",
                    posterPath = "/mSCCFODBAn6HE1fY3jbRegPJWZ0.jpg",
                    backdropPath = "/tM5nwmohCwKInwqbHyQ8WX9W75E.jpg",
                    releaseDate = "1969-02-21",
                    voteAverage = 7.5,
                    voteCount = 4,
                    popularity = 0.1803,
                    genreIds = listOf("18"),
                    originalLanguage = "de",
                    originalTitle = "Kampf um Rom II - Der Verrat",
                    adult = false,
                    video = false
                ),
                Movie(
                    id = 1059264,
                    title = "The Inseparables",
                    overview = "The animated buddy movie follows the misadventures of Don, a runaway puppet with boundless imaginations, and DJ Doggy Dog, an abandoned plush looking for a friend, who cross paths in Central Park and team up against all odds for a epic friendship adventure in New York.",
                    posterPath = "/dmftU2Rnmv9KTteMUwjalOuZHke.jpg",
                    backdropPath = "/6rhFfbI03esU1FlhEsa0OvoGCym.jpg",
                    releaseDate = "2023-09-01",
                    voteAverage = 6.472,
                    voteCount = 53,
                    popularity = 0.9984,
                    genreIds = listOf("16"),
                    originalLanguage = "en",
                    originalTitle = "The Inseparables",
                    adult = false,
                    video = false
                )
            )

            MaterialTheme {
                DetailBodyContent(
                    movieDetail = sampleMovie,
                    movies = movieList,
                    isMovieLoading = false,
                    fetchMovies = {},
                    onMovieClick = {},
                )
            }
            //MoventureTheme {
            //HomeScreen { }
            //}
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoventureTheme {
        Greeting("Android")
    }
}