package com.example.kaizentest.model

import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("d")
    val description: String,
    @SerializedName("i")
    val id: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("sh")
    val sportItem: String,
    @SerializedName("tt")
    val time: Long,
    var isFavorite: Boolean = false
)
