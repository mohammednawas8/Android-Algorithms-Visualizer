package com.example.algorithmsvisualizer.di.modules

import com.example.algorithmsvisualizer.algorithms.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AlgorithmVisualizerModule {

    @Provides
    fun provideSelectionSort() = SelectionSort()

    @Provides
    fun provideInsertionSort() = InsertionSort()

    @Provides
    fun provideBubbleSort() = BubbleSort()

    @Provides
    fun provideMergeSort() = MergeSort()

    @Provides
    fun provideHeapSort() = HeapSort()

    @Provides
    fun provideQuickSort() = QuickSort()

}