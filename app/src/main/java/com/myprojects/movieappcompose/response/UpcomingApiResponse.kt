package com.myprojects.movieappcompose.response

data class UpcomingApiResponse(
    val dates: DatesX,
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)
