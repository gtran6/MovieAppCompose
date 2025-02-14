package com.myprojects.movieappcompose.api

import com.myprojects.movieappcompose.response.ApiResponse
import com.myprojects.movieappcompose.response.PopularApiResponse
import com.myprojects.movieappcompose.response.UpcomingApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovieList(@Query("api_key") api_key : String) : ApiResponse

    @GET("/3/movie/popular")
    suspend fun getPopularMovieList(@Query("api_key") api_key: String) : PopularApiResponse

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovieList(@Query("api_key") api_key: String) : UpcomingApiResponse
}