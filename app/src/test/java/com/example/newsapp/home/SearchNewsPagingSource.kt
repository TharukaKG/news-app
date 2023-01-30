package com.example.newsapp.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.models.News

class SearchNewsPagingSource: PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        TODO("Not yet implemented")
    }

}
