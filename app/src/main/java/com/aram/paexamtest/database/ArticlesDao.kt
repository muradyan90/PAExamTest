package com.aram.paexamtest.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArtcles(articlesList: List<ArticlesEntity>)

    @Update
    fun updateArticle(articlesEntity: ArticlesEntity)

    @Delete
    fun deleteArticle(articlesEntity: ArticlesEntity)

    @Query(" SELECT * FROM articles_entity")
    fun getAllArticles(): LiveData<List<ArticlesEntity>>

    @Query("SELECT * FROM articles_entity WHERE id=:id")
    fun getArticle(id: String): ArticlesEntity
}