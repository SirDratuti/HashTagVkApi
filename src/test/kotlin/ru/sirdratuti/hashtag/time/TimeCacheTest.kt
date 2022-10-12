package ru.sirdratuti.hashtag.time

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TimeCacheTest {
    @Test
    fun `should convert current time in seconds`() {
        val currentTime = TimeCache.currentTime
        val now = System.currentTimeMillis() / TimeCache.MILLIS_TO_SECONDS
        assertEquals(
            expected = now,
            actual = currentTime,
        )
    }
}