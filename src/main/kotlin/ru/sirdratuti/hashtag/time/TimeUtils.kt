package ru.sirdratuti.hashtag.time

import ru.sirdratuti.hashtag.network.data.PostsResponse
import ru.sirdratuti.hashtag.network.data.ResolvedPost

internal fun hoursBeforeToUnix(
    hoursBefore: Int,
) = TimeCache.currentTime - SECONDS_TO_HOURS * hoursBefore

internal fun PostsResponse.mapToHoursBefore() =
    response.posts.map {
        ResolvedPost(
            hoursBefore = resolveHoursBefore(it.date).toInt()
        )
    }

private fun resolveHoursBefore(postDate: Long) =
    (TimeCache.currentTime - postDate) / SECONDS_TO_HOURS + 1

internal const val SECONDS_TO_HOURS = 3600