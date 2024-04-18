package com.example.thenewsapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    @SerializedName("article_id")
    val articleID: String,
    val title: String,
    val link: String,
    val keywords: List<String>? = null,
    val creator: List<String>? = null,
    @SerializedName("video_url")
    val videoURL: String? = null,
    val description: String,
    val content: String,
    val pubDate: String,
    @SerializedName("image_url")
    val imageURL: String,
    @SerializedName("source_id")
    val sourceID: String,
    @SerializedName("source_priority")
    val sourcePriority: Long,
    @SerializedName("source_url")
    val sourceURL: String,
    @SerializedName("source_icon")
    val sourceIcon: String? = null,
    val language: String,
    val country: List<String>,
    val category: List<String>,
    @SerializedName("ai_tag")
    val aiTag: String,
    val sentiment: String,
    @SerializedName("sentiment_stats")
    val sentimentStats: String,
    @SerializedName("ai_region")
    val aiRegion: String
) : Serializable
