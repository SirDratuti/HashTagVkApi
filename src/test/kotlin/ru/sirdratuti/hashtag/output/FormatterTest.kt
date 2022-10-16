package ru.sirdratuti.hashtag.output

import org.junit.jupiter.api.Test
import ru.sirdratuti.hashtag.network.data.ResolvedPost
import ru.sirdratuti.hashtag.state.ResponseState
import kotlin.test.assertEquals

internal class FormatterTest {

    @Test
    fun `should form success response`() {
        val response = ResponseState.Success(
            value = mutableListOf<ResolvedPost>().apply {
                repeat(4) { add(resolvedPost { 1 }) }
                repeat(2) { add(resolvedPost { 2 }) }
                repeat(4) { add(resolvedPost { 3 }) }
            }
        )

        assertEquals(
            expected = FORMED_POSTS,
            actual = response.formPosts(),
        )
    }

    @Test
    fun `should form failed response`() {
        val response = ResponseState.Failure(NETWORK_ERROR)

        assertEquals(
            expected = NETWORK_ERROR,
            actual = response.formPosts(),
        )
    }

    private fun resolvedPost(hoursProvider: () -> Int) =
        ResolvedPost(hoursBefore = hoursProvider.invoke())

    private companion object {
        private const val NETWORK_ERROR = "Network error"
        private val FORMED_POSTS = """
                1(4) ****
                2(2) **
                3(4) ****
                
            """.trimIndent()
    }
}
