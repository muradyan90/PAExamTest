package com.aram.paexamtest.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("/search")
    fun getArticles(@QueryMap queryParams: Map<String,String>): Deferred<Responce>
}