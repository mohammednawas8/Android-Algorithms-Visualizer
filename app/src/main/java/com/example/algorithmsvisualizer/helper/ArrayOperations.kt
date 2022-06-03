package com.example.algorithmsvisualizer.helper

import android.util.Log

class ArrayOperations {

    companion object {

        // { 4 , 2 , 7 , 1 , 8 }
        fun deleteArrayElement(arr: Array<Int>, index: Int): Array<Int> {

            val mutableArray = arr.toMutableList()
            mutableArray.removeAt(index)
            val tempArr = Array<Int>(mutableArray.size, init = {
                0
            })
            mutableArray.forEachIndexed { i, item ->
                tempArr[i] = item
            }
            return tempArr
        }

    }
}