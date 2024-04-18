package com.example.thenewsapp.model

data class TopNewResponse(
    val status: String,
    val totalResults: Long,
    val results: List<News>,
    val nextPage: String
)
