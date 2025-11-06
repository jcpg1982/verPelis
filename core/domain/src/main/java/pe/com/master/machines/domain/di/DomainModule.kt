package pe.com.master.machines.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.domain.repository.combine.GetAllCharactersCombineUseCase
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterByNameCombineUseCase
import pe.com.master.machines.domain.repository.combine.GetSingleMovieCombineUseCase
import pe.com.master.machines.domain.repository.database.GetAllCharactersLocalUseCase
import pe.com.master.machines.domain.repository.database.GetSingleCharacterByNameLocalUseCase
import pe.com.master.machines.domain.repository.database.GetSingleCharacterLocalUseCase
import pe.com.master.machines.domain.repository.database.SaveAllCharactersLocalUseCase
import pe.com.master.machines.domain.repository.firebase.SendLogEventAnalyticsUsesCase
import pe.com.master.machines.domain.repository.preferences.TotalPagesUsesCase
import pe.com.master.machines.domain.repository.remote.GetAllCharactersRemoteUseCase
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterByNameRemoteUseCase
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterRemoteUseCase
import pe.com.master.machines.domain.repositoryImpl.combine.GetAllCharactersCombineUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.combine.GetSingleCharacterByNameCombineUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.combine.GetSingleMovieCombineUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.database.GetAllCharactersLocalUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.database.GetSingleCharacterByNameLocalUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.database.GetSingleCharacterLocalUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.database.SaveAllCharactersLocalUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.firebase.SendLogEventAnalyticsUsesCaseImpl
import pe.com.master.machines.domain.repositoryImpl.preferences.TotalPagesUsesCaseImpl
import pe.com.master.machines.domain.repositoryImpl.remote.GetAllCharactersRemoteUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.remote.GetSingleCharacterByNameRemoteUseCaseImpl
import pe.com.master.machines.domain.repositoryImpl.remote.GetSingleCharacterRemoteUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun providerGetAllCharactersUseCase(impl: GetAllCharactersLocalUseCaseImpl): GetAllCharactersLocalUseCase

    @Binds
    @Singleton
    abstract fun providerGetSingleCharacterLocalUseCase(impl: GetSingleCharacterLocalUseCaseImpl): GetSingleCharacterLocalUseCase

    @Binds
    @Singleton
    abstract fun providerSaveAllCharactersUseCase(impl: SaveAllCharactersLocalUseCaseImpl): SaveAllCharactersLocalUseCase

    @Binds
    @Singleton
    abstract fun providerGetAllCharactersRemoteUseCase(impl: GetAllCharactersRemoteUseCaseImpl): GetAllCharactersRemoteUseCase

    @Binds
    @Singleton
    abstract fun providerGetSingleCharacterRemoteUseCase(impl: GetSingleCharacterRemoteUseCaseImpl): GetSingleCharacterRemoteUseCase

    @Binds
    @Singleton
    abstract fun providerGetAllCharactersCombineUseCase(impl: GetAllCharactersCombineUseCaseImpl): GetAllCharactersCombineUseCase

    @Binds
    @Singleton
    abstract fun providerGetSingleCharactersCombineUseCase(impl: GetSingleMovieCombineUseCaseImpl): GetSingleMovieCombineUseCase

    @Binds
    @Singleton
    abstract fun providerTotalPagesUsesCase(impl: TotalPagesUsesCaseImpl): TotalPagesUsesCase

    @Binds
    @Singleton
    abstract fun providerGetSingleCharacterByNameLocalUseCase(impl: GetSingleCharacterByNameLocalUseCaseImpl): GetSingleCharacterByNameLocalUseCase

    @Binds
    @Singleton
    abstract fun providerGetSingleCharacterByNameRemoteUseCase(impl: GetSingleCharacterByNameRemoteUseCaseImpl): GetSingleCharacterByNameRemoteUseCase

    @Binds
    @Singleton
    abstract fun providerGetSingleCharacterByNameCombineUseCase(impl: GetSingleCharacterByNameCombineUseCaseImpl): GetSingleCharacterByNameCombineUseCase

    @Binds
    @Singleton
    abstract fun providerSendLogEventAnalyticsUsesCase(impl: SendLogEventAnalyticsUsesCaseImpl): SendLogEventAnalyticsUsesCase
}