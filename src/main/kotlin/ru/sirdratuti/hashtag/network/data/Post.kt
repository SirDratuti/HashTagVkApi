package ru.sirdratuti.hashtag.network.data

import com.google.gson.annotations.SerializedName

internal data class PostsResponse(
    @SerializedName("response")
    val response: Posts,
)

internal data class Posts(
    @SerializedName("items")
    val posts: List<Post>,
)

internal data class Post(
    @SerializedName("id")
    val id: Long,
    @SerializedName("date")
    val date: Long,
)
