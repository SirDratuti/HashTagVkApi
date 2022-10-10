package ru.sirdratuti.hashtag.interactor

import ru.sirdratuti.hashtag.ACCESS_TOKEN
import ru.sirdratuti.hashtag.network.ResponseState
import ru.sirdratuti.hashtag.network.api.VERSION
import ru.sirdratuti.hashtag.network.api.service
import ru.sirdratuti.hashtag.time.hoursBeforeToUnix
import ru.sirdratuti.hashtag.time.mapToHoursBefore

internal class NewsFeedInteractor {
    internal fun getPosts(
        queryString: String,
        hoursBefore: Int = DEFAULT_HOURS_BEFORE,
        count: Int = DEFAULT_COUNT,
    ): ResponseState {
        val response = service.getNewsFeed(
            queryString = queryString,
            accessToken = ACCESS_TOKEN,
            apiVersion = VERSION,
            startTime = hoursBeforeToUnix(hoursBefore),
            count = count,
        ).execute()
        return when {
            response.isSuccessful -> ResponseState.Success(
                value = response.body()?.mapToHoursBefore()
            )

            else -> ResponseState.Failure(response.message())
        }
    }

    private companion object {
        private const val DEFAULT_HOURS_BEFORE = 1
        private const val DEFAULT_COUNT = 100
    }
}