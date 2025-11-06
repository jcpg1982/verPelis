package pe.com.master.machines.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.network.repository.MovieNetworkRepository
import pe.com.master.machines.network.repositoryImpl.MovieNetworkRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryModule {

    @Binds
    abstract fun provideMovieNetworkRepository(impl: MovieNetworkRepositoryImpl): MovieNetworkRepository

}