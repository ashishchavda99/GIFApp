package com.rabstract.gifapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rabstract.domain.usecase.trending.GetTrendingGifsUseCase
import com.rabstract.domain.usecase.trending.RemoveTrendingGifUseCase
import com.rabstract.domain.usecase.trending.SaveTrendingGifUseCase
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TrendingGifViewModel(private val getTrendingGifsUseCase: GetTrendingGifsUseCase,
                           private val removeTrendingGifUseCase: RemoveTrendingGifUseCase,
                           private val saveTrendingGifUseCase: SaveTrendingGifUseCase
) : ViewModel() {
    private val _gifs = MutableStateFlow<PagingData<GifData>>(PagingData.empty())

    val gifs: SharedFlow<PagingData<GifData>> = _gifs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PagingData.empty()
    )

    fun fetchGifsFromRemote(query: String? = null) = viewModelScope.launch {

        getTrendingGifsUseCase.execute(query).collectLatest {
            _gifs.value = it
        }
    }

    fun handleFavoriteButton(gifData: GifData) {
        viewModelScope.launch {
                if (gifData.isFavourite) saveTrendingGifUseCase.execute(gifData)
                else removeTrendingGifUseCase.execute(gifData)
        }
    }
}
