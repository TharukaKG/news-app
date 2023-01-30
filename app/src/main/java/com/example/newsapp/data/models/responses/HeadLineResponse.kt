package com.example.newsapp.data.models.responses

import com.example.newsapp.data.models.Headline

data class HeadLineResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<Headline>
)
