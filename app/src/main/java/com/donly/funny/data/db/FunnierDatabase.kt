package com.donly.funny.data.dao

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database()
abstract class FunnierDatabase: RoomDatabase() {
    companion object {
        const val TABLE_NAME = "FunnierDb"
        private var instance: RoomDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance = Room.databaseBuilder(context, FunnierDatabase::class.java, TABLE_NAME)
                .build()
            instance
        }
    }



}