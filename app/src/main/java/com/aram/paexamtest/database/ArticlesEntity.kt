package com.aram.paexamtest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles_entity")
 data class ArticlesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "web_title")
    val webTitle: String?,
    @ColumnInfo(name = "web_url")
    val webUrl: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "is_deleted")
    var isDeleted: Boolean,
    @ColumnInfo(name = "is_licked")
    var isLicked: Boolean
)