/**
 * Created by developer on 09-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.details

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.widget.ContentLoadingProgressBar
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                animeDetail = uiState.details
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
        Column {
            Text(
                text = animeDetail.title
            )
        }
    }
    
}