package ru.sirdratuti.hashtag.time

internal object TimeCache {
    internal val currentTime
        get() = System.currentTimeMillis() / MILLIS_TO_SECONDS

    internal const val MILLIS_TO_SECONDS = 1000
}