import ru.sirdratuti.hashtag.interactor.NewsFeedInteractor
import ru.sirdratuti.hashtag.network.api.service
import ru.sirdratuti.hashtag.output.formPosts

fun main() {
    val interactor = NewsFeedInteractor(
        service = service,
    )

    val response = interactor.getPosts(
        queryString = QUERY_STRING,
        hoursBefore = HOURS_BEFORE,
        count = COUNT,
    )

    println(response.formPosts())
}

private const val QUERY_STRING = "New year"
private const val HOURS_BEFORE = 5
private const val COUNT = 200
