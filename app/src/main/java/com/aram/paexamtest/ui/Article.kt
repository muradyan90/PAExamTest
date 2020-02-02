package com.aram.paexamtest.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: String,
    val type: String?,
    val webTitle: String?,
    val webUrl: String?,
    val imageUrl: String?,
    var isDeleted: Boolean,
    var isLicked: Boolean
): Parcelable