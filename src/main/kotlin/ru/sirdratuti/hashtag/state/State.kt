package ru.sirdratuti.hashtag.state

internal sealed class ResponseState {
    data class Success<T>(val value: T) : ResponseState()
    data class Failure(val error: String) : ResponseState()
}