package com.example.room101.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object RoomCreator {


    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ToDoEntity ADD COLUMN priority INTEGER NOT NULL DEFAULT 0")
        }
    }

    fun getDb(context: Context): ToDoAppDb {
        return Room.databaseBuilder(context, ToDoAppDb::class.java, "to-do")
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}