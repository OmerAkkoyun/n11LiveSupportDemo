package com.omerakkoyun.n11livesupportdemo.di

import com.omerakkoyun.n11livesupportdemo.data.WebSocketClient
import com.omerakkoyun.n11livesupportdemo.data.repository.ChatRepository
import com.omerakkoyun.n11livesupportdemo.domain.repository.IChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideWebSocketClient(): WebSocketClient {
        return WebSocketClient()
    }

    @Provides
    fun provideChatRepository(webSocketClient: WebSocketClient): IChatRepository {
        return ChatRepository(webSocketClient)
    }




}