/**
 * Created by developer on 09-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.details

import android.webkit.WebView
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.psyluckco.anibay.data.model.AnimeDetail
import com.psyluckco.anibay.home.HomeContent

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    animeId: Int?,
    onBackClicked: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        animeId.let {
            viewModel.getDetails(it)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        
        paddingValues ->
            
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

        if(uiState.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading...")
            }
        } else {
            DetailsContent(
                animeDetail = uiState.details,
                modifier = modifier.padding(paddingValues)
            )
        }

    }

}


@Composable
fun DetailsContent(
    animeDetail: AnimeDetail?,
    modifier: Modifier = Modifier
) {
    if(animeDetail == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "An error occurred")
        }
    } else {
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            if(animeDetail.trailer != null) {
                YouTubePlayer(youtubeVideoId = animeDetail.trailer.youtubeId, lifecycleOwner = LocalLifecycleOwner.current)
            }

            Text(
                text = animeDetail.title,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(text = animeDetail.plot)
        }
    }
    
}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { context->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )
}