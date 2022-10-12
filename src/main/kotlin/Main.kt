import ru.sirdratuti.hashtag.interactor.NewsFeedInteractor
import ru.sirdratuti.hashtag.network.api.service

fun main() {
    val interactor = NewsFeedInteractor(
        service = service
    )

    val response = interactor.getPosts(
        queryString = "New year",
        hoursBefore = 10,
        count = 200
    )

    println(response)
}