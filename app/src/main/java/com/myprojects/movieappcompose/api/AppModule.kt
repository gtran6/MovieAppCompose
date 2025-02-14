package com.myprojects.movieappcompose.api

import com.myprojects.movieappcompose.repository.MainRepository
import com.myprojects.movieappcompose.utils.Utils.BASE_URL
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
    fun provideRepository(movieInterface: MovieInterface) : MainRepository = MainRepository(movieInterface)

    @Singleton
    @Provides
    fun provideRetrofitInstance() : MovieInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieInterface::class.java)
    }
}