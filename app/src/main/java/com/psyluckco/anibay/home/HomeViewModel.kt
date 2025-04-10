/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psyluckco.anibay.R
import com.psyluckco.anibay.data.model.Anime
import com.psyluckco.anibay.data.repository.AnimeRepository
import com.psyluckco.anibay.utils.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val animes: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

/**
 * View model for the home screen
 *
 * @constructor Create Home view model
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _animes = animeRepository.getAnimesStream()
        .map { Async.Success(it) }
        .catch<Async<List<Anime>>> { emit(Async.Error(R.string.placeholder)) }

//    init {
//        viewModelScope.launch {
//            animeRepository.getAnimesStream()
//        }
//    }

    val uiState: StateFlow<HomeUiState> = combine(_isLoading,_animes) {
        isLoading,animes ->
            when(animes) {
                Async.Loading -> {
                    HomeUiState(isLoading = true)
                }
                is Async.Error -> {
                    HomeUiState(
                        isLoading = false,
                        userMessage = animes.errorMessage
                    )
                }
                is Async.Success -> {
                    HomeUiState(
                        isLoading = false,
                        animes = animes.data
                    )
                }
            }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState(isLoading = true)
    )

}