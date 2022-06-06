package com.example.kaizentest.model

import com.google.gson.annotations.SerializedName

data class Sports(
        @SerializedName("i")
        val id: String,
        @SerializedName("d")
        val description: String,
        @SerializedName("e")
        val events: ArrayList<Events>,
        var isExpanded: Boolean = false
)