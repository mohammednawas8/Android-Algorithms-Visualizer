package com.example.algorithmsvisualizer.di.modules

import com.example.algorithmsvisualizer.algorithms.AlgorithmsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AlgorithmVisualizerModule {

    @Provides
    fun provideInsertionSort() = AlgorithmsImpl()
}