package com.aram.paexamtest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aram.paexamtest.ArticlesApplication
import com.aram.paexamtest.database.ArticlesDao
import com.aram.paexamtest.database.ArticlesEntity
import com.aram.paexamtest.network.ApiService
import com.aram.paexamtest.ui.Article
import com.aram.paexamtest.utils.articleAsArticleEntity
import com.aram.paexamtest.utils.articleEntityAsArticle
import com.aram.paexamtest.utils.articleNetAsArticle
import com.aram.paexamtest.utils.articleNetAsArticleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface ArticlesRepositoryInerface {
    val articlesFromDb: LiveData<List<ArticlesEntity>>
    val repoJobe: Job

    suspend fun getArticles(page: String)
    fun deleteArticle(article: Article)
    fun lickeArticle(article: Article)
}

class ArticlesRepository(
    private val apiService: ApiService,
    private val articlesDao: ArticlesDao
) : ArticlesRepositoryInerface {
    private val LOG = "TAG"

    override val articlesFromDb = articlesDao.getAllArticles()

    override val repoJobe = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + repoJobe)

    private var queryParams =
        mutableMapOf(
            "q" to "articles",
            "pageSize" to "10",
            "show-fields" to "thumbnail",
            "api-key" to "1841ef16-e404-452b-bde8-0ff6d3a3e332"
        )


    override suspend fun getArticles(page: String) {
        queryParams["page"] = page

        if (ArticlesApplication.isNetworkConnected()) {
            apiService.getArticles(queryParams).await().apply {
                saveArticlesIndb(responce?.articles?.articleNetAsArticleEntity())
                Log.d(LOG, "post value net response ${responce?.articles}")
            }
        }
    }

    private fun saveArticlesIndb(articlesEntity: List<ArticlesEntity>?) {
        coroutineScope.launch {
            articlesEntity?.let {
                articlesDao.insertArtcles(it)
            }
        }
    }

    override fun deleteArticle(article: Article) {
        coroutineScope.launch {
            val deletedArticle = article.articleAsArticleEntity()
            deletedArticle.isDeleted = true
            articlesDao.updateArticle(deletedArticle)
        }
    }

    override fun lickeArticle(article: Article) {
        coroutineScope.launch {
            val lickedArticle = article.articleAsArticleEntity()
            lickedArticle.isLicked = !lickedArticle.isLicked
            articlesDao.updateArticle(lickedArticle)
        }
    }
}