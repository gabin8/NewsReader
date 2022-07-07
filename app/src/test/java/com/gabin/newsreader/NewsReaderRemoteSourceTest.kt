package com.gabin.newsreader

import com.gabin.newsreader.data.DateSerializer
import com.gabin.newsreader.data.models.NewsItem
import com.gabin.newsreader.data.models.NewsSource
import com.gabin.newsreader.data.remotesource.NewsReaderRemoteSourceImpl
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class NewsReaderRemoteSourceTest {

    private val mockHttpEngine = MockEngine {
        respond(
            content = MOCK_JSON_RESPONSE,
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private val mockClient = HttpClient(mockHttpEngine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                serializersModule = SerializersModule {
                    contextual(DateSerializer)
                }
            })
        }
    }

    private val newsReaderRemoteSource = NewsReaderRemoteSourceImpl(mockClient)
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkNewsItemParsingIsCorrect() = runTest {
        val newsItem = newsReaderRemoteSource.fetchNewsFeed().first().items.first()

        val expectedNewsItem = NewsItem(
            author = "Coryanne Hicks",
            newsSource = NewsSource("Kiplinger\'s Personal Finance"),
            title = "7 Common Investing Myths, Debunked",
            description = "If there's one thing the financial world will never run short on, it's suggestions. No matter where you turn in this industry, you'll find all the investing advice you could ever want.\nUnfortunately, you're bound to run into investing myths, too.\nYou don't ha…",
            url = "https://www.kiplinger.com/investing/604894/7-common-investing-myths-debunked",
            imageUrl = "https://mediacloud.kiplinger.com/image/private/s--X-WVjvBW--/f_auto,t_content-image-full-desktop@1/v1657132359/Investing/Investing-myths-2022.jpg",
            publishedAt = simpleDateFormat.parse("2022-07-06T18:46:28Z")!!,
            content = "If there's one thing the financial world will never run short on, it's suggestions. No matter where you turn in this industry, you'll find all the investing advice you could ever want.\r\nUnfortunately… [+11498 chars]"
        )

        assertEquals(newsItem, expectedNewsItem)
    }

    companion object {

        const val MOCK_JSON_RESPONSE =
            "{\"status\":\"ok\",\"totalResults\":14232,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Kiplinger's Personal Finance\"},\"author\":\"Coryanne Hicks\",\"title\":\"7 Common Investing Myths, Debunked\",\"description\":\"If there's one thing the financial world will never run short on, it's suggestions. No matter where you turn in this industry, you'll find all the investing advice you could ever want.\\nUnfortunately, you're bound to run into investing myths, too.\\nYou don't ha…\",\"url\":\"https://www.kiplinger.com/investing/604894/7-common-investing-myths-debunked\",\"urlToImage\":\"https://mediacloud.kiplinger.com/image/private/s--X-WVjvBW--/f_auto,t_content-image-full-desktop@1/v1657132359/Investing/Investing-myths-2022.jpg\",\"publishedAt\":\"2022-07-06T18:46:28Z\",\"content\":\"If there's one thing the financial world will never run short on, it's suggestions. No matter where you turn in this industry, you'll find all the investing advice you could ever want.\\r\\nUnfortunately… [+11498 chars]\"}]}"
    }
}
