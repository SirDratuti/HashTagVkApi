package ru.sirdratuti.hashtag.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sirdratuti.hashtag.network.data.PostsResponse

interface VKApi {
    @GET(NEWSFEED)
    fun getNewsFeed(
        @Query("q") queryString: String,
        @Query("access_token") accessToken: String,
        @Query("v") apiVersion: String,
        @Query("start_time") startTime: Long,
        @Query("count") count: Int,
    ): Response<PostsResponse>

    companion object {
        private const val NEWSFEED = "newsfeed.search"
        internal const val VERSION = "5.131"
    }
}