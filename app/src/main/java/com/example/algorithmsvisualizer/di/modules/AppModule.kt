package com.example.algorithmsvisualizer.di.modules

import android.content.Context
import com.example.algorithmsvisualizer.data.db.AlgorithmDatabase
import com.example.algorithmsvisualizer.repo.AlgorithmRepository
import com.example.algorithmsvisualizer.repo.AlgorithmRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AlgorithmDatabase = AlgorithmDatabase.getDatabaseInstance(context)

    @Provides
    @Singleton
    fun provideAlgorithmRepository(
        database: AlgorithmDatabase
    ): AlgorithmRepository = AlgorithmRepositoryImpl(database)

}
