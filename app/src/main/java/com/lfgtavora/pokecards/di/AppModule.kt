package com.lfgtavora.pokecards.di

import android.content.Context
import androidx.room.Room
import com.lfgtavora.pokecards.data.local.AppDatabase
import com.lfgtavora.pokecards.data.remote.PokemonTcgApi
import com.lfgtavora.pokecards.feature.set.data.datasource.PokemonTcgRemoteDataSource
import com.lfgtavora.pokecards.feature.set.data.datasource.PokemonTcgRemoteDataSourceImpl
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepositoryImpl
import com.lfgtavora.pokecards.feature.set.domain.usecase.GetAllSetsByDateUseCase
import com.lfgtavora.pokecards.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    ): PokemonTcgRemoteDataSource = PokemonTcgRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pokemontcg.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCardSetRepository(
        pokemonTcgRemoteDataSource: PokemonTcgRemoteDataSource
    ): CardSetRepository = CardSetRepositoryImpl(pokemonTcgRemoteDataSource)

    @Singleton
    @Provides
    fun provideGetAllSetsByDateUseCaseImpl(
        cardSetRepository: CardSetRepository
    ): GetAllSetsByDateUseCase = GetAllSetsByDateUseCase(cardSetRepository)


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