package ru.sirdratuti.hashtag.interactor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Test
import retrofit2.Call
import retrofit2.Response
import ru.sirdratuti.hashtag.network.api.VKApi
import ru.sirdratuti.hashtag.network.data.Post
import ru.sirdratuti.hashtag.network.data.Posts
import ru.sirdratuti.hashtag.network.data.PostsResponse
import ru.sirdratuti.hashtag.state.ResponseState
import ru.sirdratuti.hashtag.time.TimeCache
import ru.sirdratuti.hashtag.time.mapToHoursBefore
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class NewsFeedInteractorTest {

    @MockK
    lateinit var api: VKApi

    @MockK
    lateinit var call: Call<PostsResponse>

    @BeforeTest
    fun initMocks() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should get error state if response contains errors`() {
        mockInteractor(errorResponse())

        val state = newsFeedInteractor().getPosts(
            queryString = QUERY,
        )

        assertEquals(
            expected = true,
            actual = state is ResponseState.Failure,
        )

        assertEquals(
            expected = ERROR_RESPONSE_CONTENT,
            actual = (state as? ResponseState.Failure)?.error
        )
    }

    @Test
    fun `should get success state if response doesn't containt errors`() {
        mockInteractor(successResponse())

        val state = newsFeedInteractor().getPosts(
            queryString = QUERY
        )

        assertEquals(
            expected = true,
            actual = state is ResponseState.Success
        )

        assertEquals(
            expected = SUCCESS_RESPONSE_CONTENT.mapToHoursBefore(),
            actual = (state as? ResponseState.Success)?.value
        )
    }

    private fun mockInteractor(response: Response<PostsResponse>) {
        every { call.execute() } returns response
        every {
            api.getNewsFeed(
                queryString = QUERY,
                accessToken = any(),
                apiVersion = any(),
                startTime = any(),
                count = any(),
            )
        } returns call
    }

    private fun newsFeedInteractor() =
        NewsFeedInteractor(api)

    private fun errorResponse(): Response<PostsResponse> = Response.error(
        ERROR_CODE,
        ResponseBody.create(
            MediaType.parse(MEDIA_TYPE),
            ERROR_RESPONSE_CONTENT
        )
    )

    private fun successResponse(): Response<PostsResponse> = Response.success(
        SUCCESS_CODE,
        SUCCESS_RESPONSE_CONTENT,
    )

    private companion object {
        private const val QUERY = "QUERY"

        private const val ERROR_CODE = 404
        private const val SUCCESS_CODE = 200
        private const val MEDIA_TYPE = "application/json"
        private const val ERROR_RESPONSE_CONTENT = "RESPONSE_CONTENT"
        private val SUCCESS_RESPONSE_CONTENT = PostsResponse(
            response = Posts(
                posts = listOf(
                    Post(
                        id = 0,
                        date = TimeCache.currentTime,
                    ),
                ),
            ),
        )
    }
}
