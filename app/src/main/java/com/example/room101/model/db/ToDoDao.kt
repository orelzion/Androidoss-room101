package com.example.room101.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(toDoEntity: ToDoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(toDoEntity: ToDoEntity)

    @Delete
    fun remove(toDoEntity: ToDoEntity)

    @Query("SELECT * FROM todoentity WHERE isDone = 1")
    fun getCompletedItems(): LiveData<List<ToDoEntity>>

    @Query("SELECT * FROM todoentity WHERE isDone = 0")
    fun getUnCompletedItems(): LiveData<List<ToDoEntity>>

    @Query("SELECT * FROM todoentity")
    fun getAllItems(): LiveData<List<ToDoEntity>>
}