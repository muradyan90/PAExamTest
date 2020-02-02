package com.aram.paexamtest.vievmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aram.paexamtest.repository.ArticlesRepository
import com.aram.paexamtest.ui.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AllArticlesViewModel(
    private val repo: ArticlesRepository
): ViewModel() {

    private val LOG = "TAG"

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + viewModelJob)



    val articles = repo.articlesFromDb
    init {

        // download first page
        getArticles("1")

    }
     fun getArticles(page: String){
         coroutineScope.launch {
             repo.getArticles(page)
         }
    }

    fun likeArticle(article: Article){
     repo.lickeArticle(article)
    }

    fun deleteArticle(article: Article){
        repo.deleteArticle(article)
    }

    override fun onCleared() {
        super.onCleared()
        repo.repoJobe.cancel()
        viewModelJob.cancel()
    }
}