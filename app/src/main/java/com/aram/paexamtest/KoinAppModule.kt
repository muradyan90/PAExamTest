package com.aram.paexamtest

import androidx.room.Room
import com.aram.paexamtest.database.ArticlesDb
import com.aram.paexamtest.network.ApiService
import com.aram.paexamtest.repository.ArticlesRepository
import com.aram.paexamtest.vievmodels.AllArticlesViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://content.guardianapis.com"

val koinModule = module {

    single {
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            //.create(ApiService::class.java)
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            ArticlesDb::class.java,
            "articles_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { ArticlesRepository(get(), get<ArticlesDb>().articlesDao) }

    viewModel {
        AllArticlesViewModel(get())
    }
}