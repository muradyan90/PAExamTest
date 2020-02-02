package com.aram.paexamtest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticlesEntity::class], version = 1 , exportSchema = false)
abstract class ArticlesDb : RoomDatabase() {
    abstract val articlesDao: ArticlesDao
}