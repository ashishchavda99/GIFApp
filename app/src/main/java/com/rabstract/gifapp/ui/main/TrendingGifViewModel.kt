package com.rabstract.gifapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData

import com.rabstract.domain.usecase.trending.GetTrendingGifsUseCase
import com.rabstract.model.GifData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.rabstract.core.datatype.Result
import com.rabstract.core.datatype.ResultType
import com.rabstract.domain.usecase.trending.RemoveTrendingGifUseCase
import com.rabstract.domain.usecase.trending.SaveTrendingGifUseCase
import kotlinx.coroutines.flow.*

class TrendingGifViewModel(private val getTrendingGifsUseCase: GetTrendingGifsUseCase,
                           private val removeTrendingGifUseCase: RemoveTrendingGifUseCase,
                           private val saveTrendingGifUseCase: SaveTrendingGifUseCase
) : ViewModel() {
    /*private val gifTrendingGifsMutableLiveData: MutableLiveData<List<GifData>> = MutableLiveData()
    val gifTrendingGifsLiveData: LiveData<List<GifData>>
        get() = gifTrendingGifsMutableLiveData

    private val areEmptyTrendingGifsMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val areEmptyTrendingGifsLiveData: LiveData<Boolean>
        get() = areEmptyTrendingGifsMutableLiveData

    private val isErrorMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = isErrorMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean>
        get() = isLoadingMutableLiveData
*/
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

    /*  private fun updateAppropriateLiveData(result: Result<List<GifData>>) {
         if (isResultSuccess(result.resultType)) {
             onResultSuccess(result.data)
         } else {
             onResultError()
         }
     }

    private fun isResultSuccess(resultType: ResultType): Boolean {
         return resultType == ResultType.SUCCESS
     }

     private fun onResultSuccess(list: List<GifData>?) {
         if (list?.isEmpty() != true) {
             gifTrendingGifsMutableLiveData.value = list
         } else {
             areEmptyTrendingGifsMutableLiveData.value = true
         }

         isLoadingLiveData(false)
     }

     *//**
     *  The delay is to avoid the screen flash between the transition from AlertDialog to ProgressBar
     * *//*
    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingLiveData(false)
        }.invokeOnCompletion {
            isErrorMutableLiveData.value = true
        }
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingMutableLiveData.value = isLoading
    }*/
    fun handleFavoriteButton(gifData: GifData) {
        viewModelScope.launch {
                if (gifData.isFavourite) saveTrendingGifUseCase.execute(gifData)
                else removeTrendingGifUseCase.execute(gifData)
        }
    }
}
