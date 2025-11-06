package pe.com.master.machines.firebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.firebase.repository.FirebaseRepository
import pe.com.master.machines.firebase.repositoryImpl.FirebaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    abstract fun provideFirebaseRepository(impl: FirebaseRepositoryImpl): FirebaseRepository

}