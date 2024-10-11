package com.example.eventbookingapp.module

import com.example.eventbookingapp.BuildConfig
import com.example.eventbookingapp.repository.implementation.LocationRepositoryImpl
import com.example.eventbookingapp.repository.implementation.TokenRepositoryImpl
import com.example.eventbookingapp.repository.repository_interface.LocationRepository
import com.example.eventbookingapp.repository.repository_interface.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTokenRepository(): TokenRepository {
        /**
         * 모듈 테스트 시, Dispatchers를 변경해서 넣을 수 있음
         * 지금은 아직 방법을 몰라서 같은 Dispatchers를 넣었습니다.
         */
        val dispatchers: CoroutineDispatcher = if (BuildConfig.DEBUG) {
            Dispatchers.IO
        } else {
            Dispatchers.IO
        }
        return TokenRepositoryImpl(dispatchers)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(): LocationRepository = LocationRepositoryImpl()
}