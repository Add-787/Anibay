/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.psyluckco.anibay.data.model.Anime
import com.psyluckco.anibay.ui.theme.AnibayTheme
import kotlin.contracts.contract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAnimeClicked: (Int) -> Unit,
    snackbarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    },
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Anibay") })
        }
    ) {
        paddingValues ->

            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            HomeContent(
                isLoading = uiState.isLoading,
                animes = uiState.animes,
                onAnimeClick = onAnimeClicked,
                modifier = modifier.padding(paddingValues)
            )

    }

}

@Composable
fun HomeContent(
    isLoading: Boolean,
    animes: List<Anime>,
    onAnimeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if(animes.isEmpty()) {
            EmptyContent()
        } else {
            AnimeList(
                animes = animes,
                onAnimeClicked = onAnimeClick
            )
        }
    }

}

@Composable
fun EmptyContent(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "No animes found",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun AnimeList(
    animes: List<Anime> = emptyList<Anime>(),
    onAnimeClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(animes) {
            anime -> AnimeItem(
                anime = anime,
                onItemClicked = onAnimeClicked
            )
                
        }
    }

}

@Composable
fun AnimeItem(
    anime: Anime,
    onItemClicked: (Int) -> Unit = { },
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(anime.title) },
        leadingContent = {
            // Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            AsyncImage(
                model = anime.imgUrl,
                contentDescription = null,
                modifier = modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )
        },
        trailingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null)
                Text(text = anime.rating.toString())
            }
        },
        modifier = modifier.clickable(onClick = { onItemClicked(anime.id) })
    )
}

@Preview
@Composable
private fun AnimeItemPreview() {
    AnibayTheme {
        AnimeItem(
            anime = Anime(
                id = 3,
                title = "Cowboy Bebop",
                noOfEpisodes = 4,
                rating = 4.5,
                imgUrl = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SomeAnimesPreview() {
    AnibayTheme {
        HomeContent(
            isLoading = false,
            animes = listOf(
                Anime(
                    id = 3,
                    title = "Cowboy Bebop",
                    noOfEpisodes = 4,
                    rating = 4.5,
                    imgUrl = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"
                ),
                Anime(
                    id = 4,
                    title = "Cowboy Bebop",
                    noOfEpisodes = 4,
                    rating = 4.5,
                    imgUrl = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"
                ),
                Anime(
                    id = 5,
                    title = "Cowboy Bebop",
                    noOfEpisodes = 4,
                    rating = 4.5,
                    imgUrl = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"
                )
            ),
            onAnimeClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyContentPreview() {
    AnibayTheme {
        HomeContent(
            isLoading = false,
            animes = emptyList(),
            onAnimeClick = { },
        )
    }
}



