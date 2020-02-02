package com.aram.paexamtest.network

import com.squareup.moshi.Json

data class Responce(
    @Json(name = "response")
    val responce: ResponcePage?
)

data class ResponcePage(
    @Json(name = "total")
    val total: Int?,
    @Json(name = "pageSize")
    val pageSize: Int?,
    @Json(name = "currentPage")
    val currentPage: Int?,
    @Json(name = "pages")
    val pages: Int?,
    @Json(name = "results")
    val articles: List<ArticleNet>?
)

data class ArticleNet(
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "webTitle")
    val webTitle: String?,
    @Json(name = "webUrl")
    val webUrl: String?,
    @Json(name = "fields")
    val fields: Fields?
)

data class Fields(
    @Json(name = "thumbnail")
    val imageUrl: String?
)