package com.myprojects.movieappcompose.response

data class PopularApiResponse(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)
