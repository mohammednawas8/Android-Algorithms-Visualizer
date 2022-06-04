package com.example.algorithmsvisualizer.di.modules

import com.example.algorithmsvisualizer.algorithms.sorting.InsertionSort
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AlgorithmVisualizerModule {

    @Provides
    fun provideInsertionSort() = InsertionSort()
}