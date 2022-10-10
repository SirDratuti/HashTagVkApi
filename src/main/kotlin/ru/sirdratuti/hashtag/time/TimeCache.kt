package ru.sirdratuti.hashtag.time

internal object TimeCache {
    internal val currentTime by lazy {
        System.currentTimeMillis() / MILLIS_TO_SECONDS
    }

    private const val MILLIS_TO_SECONDS = 1000
}