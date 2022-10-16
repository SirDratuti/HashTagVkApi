package ru.sirdratuti.hashtag.output

import ru.sirdratuti.hashtag.state.ResponseState

internal fun ResponseState.formPosts() = when (this) {
    is ResponseState.Success -> {
        val posts = value.groupBy { it.hoursBefore }.values.map { it.size }
        buildString {
            posts.forEachIndexed { index, value ->
                append(resolveString(index, value))
            }
        }
    }

    is ResponseState.Failure -> {
        error
    }
}

private fun resolveString(index: Int, value: Int) =
    "${index + 1}($value) ${POST_SYMBOL.repeat(value)}\n"

private const val POST_SYMBOL = "*"
