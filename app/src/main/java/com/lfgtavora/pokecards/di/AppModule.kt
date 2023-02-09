package com.lfgtavora.pokecards.di

import com.lfgtavora.pokecards.data.remote.PokemonTcgApi
import com.lfgtavora.pokecards.feature.set.data.datasource.CardSetsRemoteDataSource
import com.lfgtavora.pokecards.feature.set.data.datasource.CardSetsRemoteDataSourceImpl
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepositoryImpl
import com.lfgtavora.pokecards.feature.set.domain.usecase.GetAllSetsByDateUseCase
import com.lfgtavora.pokecards.feature.set.domain.usecase.GetAllSetsByDateUseCaseImpl
import com.lfgtavora.pokecards.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCardSetsRemoteDataSource(
        api: PokemonTcgApi
    ): CardSetsRemoteDataSource = CardSetsRemoteDataSourceImpl(api)

    @Singleton
    @Provides
    fun provideCardSetRepository(
        cardSetsRemoteDataSource: CardSetsRemoteDataSource
    ): CardSetRepository = CardSetRepositoryImpl(cardSetsRemoteDataSource)

    @Singleton
    @Provides
    fun provideGetAllSetsByDateUseCaseImpl(
        cardSetRepository: CardSetRepository
    ): GetAllSetsByDateUseCase = GetAllSetsByDateUseCaseImpl(cardSetRepository)

    @Singleton
    @Provides
    fun providePokemonTcgApi(): PokemonTcgApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonTcgApi::class.java)
    }
}