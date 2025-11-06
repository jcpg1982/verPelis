package pe.com.master.machines.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.database.repository.MoviesDatabaseRepository
import pe.com.master.machines.database.repositoryImpl.MoviesDatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBaseRepositoryModule {

    @Binds
    abstract fun provideCharactersDatabaseRepository(impl: MoviesDatabaseRepositoryImpl): MoviesDatabaseRepository

}