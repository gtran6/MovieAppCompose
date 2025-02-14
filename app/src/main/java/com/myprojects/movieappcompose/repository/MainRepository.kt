package com.myprojects.movieappcompose.repository

import com.myprojects.movieappcompose.api.MovieInterface
import com.myprojects.movieappcompose.response.ApiResponse
import com.myprojects.movieappcompose.response.PopularApiResponse
import com.myprojects.movieappcompose.response.UpcomingApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val movieInterface: MovieInterface) {

    fun getNowPlayingList(api_key: String) : Flow<ApiResponse> = flow {
        emit(movieInterface.getNowPlayingMovieList(api_key))
    }.flowOn(Dispatchers.IO)

    fun getPopularList(api_key: String) : Flow<PopularApiResponse> = flow {
        emit(movieInterface.getPopularMovieList(api_key))
    }.flowOn(Dispatchers.IO)

    fun getUpcomingList(api_key: String) : Flow<UpcomingApiResponse> = flow {
        emit(movieInterface.getUpcomingMovieList(api_key))
    }.flowOn(Dispatchers.IO)
}