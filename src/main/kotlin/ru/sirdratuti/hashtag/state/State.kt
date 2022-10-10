package ru.sirdratuti.hashtag.network

internal sealed class ResponseState {
    data class Success<T>(val value: T) : ResponseState()
    data class Failure(val error: String) : ResponseState()
}