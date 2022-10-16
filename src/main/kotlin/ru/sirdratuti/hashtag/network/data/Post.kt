package ru.sirdratuti.hashtag.network.data

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @SerializedName("response")
    val response: Posts,
)

data class Posts(
    @SerializedName("items")
    val posts: List<Post>,
)

data class Post(
    @SerializedName("id")
    val id: Long,
    @SerializedName("date")
    val date: Long,
)
