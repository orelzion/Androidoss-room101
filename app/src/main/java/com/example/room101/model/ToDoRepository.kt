package com.example.room101.model

import androidx.lifecycle.LiveData
import com.example.room101.model.db.ToDoDao
import com.example.room101.model.db.ToDoEntity

class ToDoRepository(private val toDoDao: ToDoDao) {

    enum class ToDoFilter {
        ALL,
        COMPLETED,
        UNCOMPLETED
    }

    fun getToDoItems(toDoFilter: ToDoFilter): LiveData<List<ToDoEntity>> {
        return when (toDoFilter) {
            ToDoFilter.ALL -> toDoDao.getAllItems()
            ToDoFilter.COMPLETED -> toDoDao.getCompletedItems()
            ToDoFilter.UNCOMPLETED -> toDoDao.getUnCompletedItems()
        }
    }

    fun addToDo(toDoText: String) {
        toDoDao.insert(ToDoEntity(text = toDoText))
    }

    fun updateToDo(toDoEntity: ToDoEntity) {
        toDoDao.insertOrUpdate(toDoEntity)
    }

    fun remove(toDoEntity: ToDoEntity) {
        toDoDao.remove(toDoEntity)
    }
}