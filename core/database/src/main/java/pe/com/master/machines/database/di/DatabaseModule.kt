package pe.com.master.machines.database.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.RealmConfiguration
import pe.com.master.machines.database.entity.MovieEntity
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRealmConfig(): RealmConfiguration =
        RealmConfiguration.Builder(
            schema = setOf(MovieEntity::class)
        ).name("movies.realm")
            .schemaVersion(1)
            .build()

}