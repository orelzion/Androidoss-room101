package com.example.room101.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoEntity::class], version = 2)
abstract class ToDoAppDb: RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao
}