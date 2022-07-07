package com.gabin.newsreader.di

import com.gabin.newsreader.data.DateSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(module: SerializersModule): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                clientCacheSize = 5
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    serializersModule = module
                })
            }
            followRedirects = true
        }
    }

    @Provides
    @Singleton
    fun provideSerializersModule() = SerializersModule {
        contextual(DateSerializer)
    }

}
