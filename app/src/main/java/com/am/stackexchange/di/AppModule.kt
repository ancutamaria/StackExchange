package com.am.stackexchange.di

import android.content.Context
import androidx.room.Room
import com.am.stackexchange.model.StackExchangeAPIService
import com.am.stackexchange.model.StackExchangeRepository
import com.am.stackexchange.model.db.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com/2.2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAPIService(retrofit: Retrofit): StackExchangeAPIService = retrofit.create(
        StackExchangeAPIService::class.java)

    @Provides
    fun provideQuestionDao(stackExchangeDatabase: StackExchangeDatabase): ItemsDao {
        return stackExchangeDatabase.itemsDao()
    }

    @Provides
    @Singleton
    fun provideStackExchangeDatabase(@ApplicationContext appContext: Context): StackExchangeDatabase {
        return Room.databaseBuilder(
            appContext,
            StackExchangeDatabase::class.java,
            "RssReader"
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideStackExchangeRepository(apiService: StackExchangeAPIService,
                                       itemsDao: ItemsDao,
    ) =
        StackExchangeRepository(apiService, itemsDao)

}