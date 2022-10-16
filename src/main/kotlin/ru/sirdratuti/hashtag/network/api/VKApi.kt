package ru.sirdratuti.hashtag.network.api

import retrofit2.Call
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
    ): Call<PostsResponse>

    companion object {
        internal const val VERSION = "5.131"
        private const val NEWSFEED = "newsfeed.search"
    }
}
