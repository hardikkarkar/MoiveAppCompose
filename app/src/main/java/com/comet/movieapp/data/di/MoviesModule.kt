package com.comet.movieapp.data.di

import com.comet.movieapp.data.remote.MoviesService
import com.comet.movieapp.data.repository.MovieRepository
import com.comet.movieapp.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}


@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}

