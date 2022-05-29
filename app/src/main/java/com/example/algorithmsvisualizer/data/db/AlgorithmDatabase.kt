package com.example.algorithmsvisualizer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmCode
import com.example.algorithmsvisualizer.data.model.AlgorithmGroup

@Database(entities = [Algorithm::class, AlgorithmGroup::class, AlgorithmCode::class], version = 1)
abstract class AlgorithmDatabase : RoomDatabase() {
    abstract val algorithmDao: AlgorithmDao

    companion object {
        @Volatile
        var INSTANCE: AlgorithmDatabase? = null

        @Synchronized
        fun getDatabaseInstance(context: Context): AlgorithmDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AlgorithmDatabase::class.java,
                        "algorithm_db"
                    ).createFromAsset("database/algorithm_db.db").fallbackToDestructiveMigration().build()
                }
                return INSTANCE!!
            }

        }
    }
}


