package com.aram.paexamtest.utils

import android.util.Log
import android.webkit.WebView
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aram.paexamtest.database.ArticlesEntity
import com.aram.paexamtest.network.ArticleNet
import com.aram.paexamtest.ui.Article
import com.aram.paexamtest.ui.ArticlesRVAdapter
import com.bumptech.glide.Glide
private val LOG = "TAG"

fun List<ArticleNet>.articleNetAsArticleEntity(): List<ArticlesEntity>{
    return map {
        ArticlesEntity(
            id = it.id ?: "null from backend",
            type = it.type,
            webTitle = it.webTitle,
            webUrl = it.webUrl,
            imageUrl = it.fields?.imageUrl,
            isDeleted = false,
            isLicked = false)
    }
}

fun List<ArticleNet>.articleNetAsArticle(): List<Article>{
    return map {
        Article(
            id = it.id ?: "null from backend",
            type = it.type,
            webTitle = it.webTitle,
            webUrl = it.webUrl,
            imageUrl = it.fields?.imageUrl,
            isDeleted = false,
            isLicked = false
        )
    }
}

fun List<ArticlesEntity>.articleEntityAsArticle(): List<Article>{
    return map {
        Article(
            id = it.id,
            type = it.type,
            webTitle = it.webTitle,
            webUrl = it.webUrl,
            imageUrl = it.imageUrl,
            isDeleted = it.isDeleted,
            isLicked = it.isLicked
        )
    }
}

fun Article.articleAsArticleEntity(): ArticlesEntity{
    return ArticlesEntity(
        id = id,
        type = type,
        webTitle = webTitle,
        webUrl = webUrl,
        imageUrl = imageUrl,
        isDeleted = isDeleted,
        isLicked = isLicked
    )
}

@BindingAdapter("dataList")
fun bindRecyclerView(recyclerView: RecyclerView, articleList: List<ArticlesEntity>?){
    articleList?.let {
        (recyclerView.adapter as ArticlesRVAdapter).apply {

            Log.d(LOG,"submit - $articleList")
            submitList(it.articleEntityAsArticle().filter {
                !it.isDeleted
            })
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImageView(imageView : ImageView, imageUrl: String?){
    Log.d(LOG,"imageUrl - $imageUrl")

    imageUrl?.let {
        Glide.with(imageView.context).load(it.toUri()).into(imageView)
    }
}

@BindingAdapter("webUrl")
fun bindWebView(webView: WebView,webUrl: String?){
    webUrl?.let {
        webView.loadUrl(it)
    }
}
