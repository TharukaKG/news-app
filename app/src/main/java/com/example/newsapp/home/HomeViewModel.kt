package com.example.newsapp.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.newsapp.data.models.Headline
import com.example.newsapp.data.models.News
import kotlinx.coroutines.flow.flow

class HomeViewModel(private val repository: HomeRepository): ViewModel() {


    val headlines:LiveData<Result<List<Headline>?>> = liveData {
        emitSource(repository.getLatestNews().asLiveData())
    }

    private val searchNews:MutableLiveData<String> = MutableLiveData()
    val searchNewsLive = searchNews.switchMap{ category->
        liveData {
            emitSource(
                repository.searchNews(category).asLiveData()
            )
        }
    }

    fun searchNews(category: String) {
        searchNews.postValue(category)
    }

}
