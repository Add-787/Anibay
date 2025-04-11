/**
 * Created by developer on 11-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psyluckco.anibay.data.model.AnimeDetail
import com.psyluckco.anibay.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailsUiState(
    val isLoading: Boolean,
    val details: AnimeDetail?
)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(
        DetailsUiState(
            isLoading = true,
            details = null
        )
    )

    val uiState: StateFlow<DetailsUiState> = _uiState

    fun getDetails(id: Int?) {
        if(id == null) {
            _uiState.update { it.copy(isLoading = false) }
            return
        }
        viewModelScope.launch {
            val details = animeRepository.getAnime(id)
            _uiState.update { it.copy(isLoading = false, details = details) }
        }
    }

}