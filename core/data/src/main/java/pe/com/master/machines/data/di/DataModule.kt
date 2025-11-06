package pe.com.master.machines.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.data.repository.firebase.FirebaseDataRepository
import pe.com.master.machines.data.repository.preferences.PreferencesDataRepository
import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.data.repositoryImpl.database.MovieLocalDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.firebase.FirebaseDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.preferences.PreferencesDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.remote.MovieRemoteDataRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideMovieDataRepository(impl: MovieRemoteDataRepositoryImpl): MovieRemoteDataRepository

    @Binds
    abstract fun provideMovieLocalDataRepository(impl: MovieLocalDataRepositoryImpl): MovieLocalDataRepository

    @Binds
    abstract fun providePreferencesDataRepository(impl: PreferencesDataRepositoryImpl): PreferencesDataRepository

    @Binds
    abstract fun provideFirebaseDataRepository(impl: FirebaseDataRepositoryImpl): FirebaseDataRepository

}