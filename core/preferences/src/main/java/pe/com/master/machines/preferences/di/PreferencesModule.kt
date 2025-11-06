package pe.com.master.machines.preferences.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.preferences.repository.PreferencesRepository
import pe.com.master.machines.preferences.repositoryImpl.PreferencesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun providePreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository

}