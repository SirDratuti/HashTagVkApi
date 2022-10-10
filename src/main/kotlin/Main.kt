import ru.sirdratuti.hashtag.interactor.NewsFeedInteractor

fun main() {
    val interactor = NewsFeedInteractor()

    val response = interactor.getPosts(
        queryString = "New year",
        hoursBefore = 10,
        count = 200
    )

    println(response)
}