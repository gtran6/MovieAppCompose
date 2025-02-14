package com.myprojects.movieappcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myprojects.movieappcompose.api.Events
import com.myprojects.movieappcompose.repository.MainRepository
import com.myprojects.movieappcompose.response.Result
import com.myprojects.movieappcompose.response.ResultX
import com.myprojects.movieappcompose.response.ResultXX
import com.myprojects.movieappcompose.utils.Utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repo: MainRepository) : ViewModel() {
    val nowPlayingList: MutableLiveData<Events<List<Result>>> = MutableLiveData()
    val popularList: MutableLiveData<Events<List<ResultX>>> = MutableLiveData()
    val upcomingList: MutableLiveData<Events<List<ResultXX>>> = MutableLiveData()

    init {
        getNowPlayingMovieList()
    }

    private fun getNowPlayingMovieList() {
        viewModelScope.launch {
            nowPlayingList.postValue(Events.Loading())
            repo.getNowPlayingList(API_KEY).catch {
                nowPlayingList.postValue(Events.Error(msg = it.localizedMessage))
            }.collect { list ->
                nowPlayingList.postValue(Events.Success(list.results))
            }
        }
    }

    fun getPopularMovieList() {
        viewModelScope.launch {
            popularList.postValue(Events.Loading())
            repo.getPopularList(API_KEY).catch {
                popularList.postValue(Events.Error(msg = it.localizedMessage))
            }.collect { list ->
                popularList.postValue(Events.Success(list.results))
            }
        }
    }

    fun getUpcomingMovieList() {
        viewModelScope.launch {
            upcomingList.postValue(Events.Loading())
            repo.getUpcomingList(API_KEY).catch {
                upcomingList.postValue(Events.Error(msg = it.localizedMessage))
            }.collect { list ->
                upcomingList.postValue(Events.Success(list.results))
            }
        }
    }
}