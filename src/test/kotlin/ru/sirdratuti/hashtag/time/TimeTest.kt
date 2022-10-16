package ru.sirdratuti.hashtag.time

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TimeTest {
    @Test
    fun `should correctly convert time to Unix`() {
        val hoursBeforeRange = 1..24
        hoursBeforeRange.forEach { hoursBefore ->
            val currentTime = TimeCache.currentTime
            val unixTimeHourBefore = hoursBeforeToUnix(hoursBefore)
            assertEquals(
                expected = currentTime - hoursBefore * SECONDS_TO_HOURS,
                actual = unixTimeHourBefore,
            )
        }
    }
}
