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
        require(count in 0..MAX_POSTS_COUNT) { "Incorrect count of posts" }
        require(hoursBefore in 1..24) { "Incorrect amount of hours" }
        val response = service.getNewsFeed(
            queryString = queryString,
            accessToken = ACCESS_TOKEN,
            apiVersion = VKApi.VERSION,
            startTime = hoursBeforeToUnix(hoursBefore),
            count = count,
        )
        return when {
            response.isSuccessful -> ResponseState.Success(
                value = response.body()?.mapToHoursBefore()
            )

            else -> ResponseState.Failure(
                error = response.errorBody()?.string().orEmpty()
            )
        }
    }

    private companion object {
        private const val DEFAULT_HOURS_BEFORE = 1
        private const val DEFAULT_COUNT = 100

        private const val MAX_POSTS_COUNT = 200
    }
}