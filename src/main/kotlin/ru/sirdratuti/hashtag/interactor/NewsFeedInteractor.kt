package ru.sirdratuti.hashtag.interactor

import ru.sirdratuti.hashtag.network.ACCESS_TOKEN
import ru.sirdratuti.hashtag.network.api.VKApi
import ru.sirdratuti.hashtag.state.ResponseState
import ru.sirdratuti.hashtag.time.hoursBeforeToUnix
import ru.sirdratuti.hashtag.time.mapToHoursBefore

class NewsFeedInteractor(
    private val service: VKApi,
) {
    internal fun getPosts(
        queryString: String,
        hoursBefore: Int = DEFAULT_HOURS_BEFORE,
        count: Int = DEFAULT_COUNT,
    ): ResponseState {
        require(count in POSTS_RANGE) { "Incorrect count of posts" }
        require(hoursBefore in HOURS_BEFORE_RANGE) { "Incorrect amount of hours" }
        val response = service.getNewsFeed(
            queryString = queryString,
            accessToken = ACCESS_TOKEN,
            apiVersion = VKApi.VERSION,
            startTime = hoursBeforeToUnix(hoursBefore),
            count = count,
        ).execute()
        return when {
            response.isSuccessful -> ResponseState.Success(
                value = response.body()?.mapToHoursBefore() ?: emptyList(),
            )

            else -> ResponseState.Failure(
                error = response.errorBody()?.string().orEmpty(),
            )
        }
    }

    private companion object {
        private const val DEFAULT_HOURS_BEFORE = 1
        private const val DEFAULT_COUNT = 100

        private val POSTS_RANGE = 0..200
        private val HOURS_BEFORE_RANGE = 1..24
    }
}
