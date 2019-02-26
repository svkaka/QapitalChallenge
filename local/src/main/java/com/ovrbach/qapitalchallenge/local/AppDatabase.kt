package com.ovrbach.qapitalchallenge.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.Goal

@Database(entities = [Feed::class, Goal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feed(): FeedDao
    abstract fun goals(): GoalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = buildDatabase(context)
                INSTANCE = instance
                instance
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "Word_database"
        ).build()
    }

}