package ru.sirdratuti.hashtag.state

import ru.sirdratuti.hashtag.network.data.ResolvedPost

internal sealed class ResponseState {
    data class Success(val value: List<ResolvedPost>) : ResponseState()
    data class Failure(val error: String) : ResponseState()
}
