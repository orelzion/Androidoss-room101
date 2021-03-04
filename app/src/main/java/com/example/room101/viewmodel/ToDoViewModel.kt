package com.example.room101.viewmodel

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.room101.model.ToDoRepository
import com.example.room101.model.db.ToDoEntity

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {

    val listLiveData = repository
        .getToDoItems(ToDoRepository.ToDoFilter.ALL)
        .map { ToDoListViewData(convertToViewData(it)) }

    private fun convertToViewData(entityList: List<ToDoEntity>): List<ToDoItemViewData> {
        return entityList.mapNotNull { entity ->
            entity.takeIf { it.id != null }?.let {
                ToDoItemViewData(
                    id = it.id!!,
                    text = it.text,
                    isDone = it.isDone
                )
            }
        }
    }

    fun onTodoChanged(toDoItemViewData: ToDoItemViewData) {
        AsyncTask.execute {
            repository.updateToDo(
                ToDoEntity(
                    toDoItemViewData.id,
                    toDoItemViewData.text,
                    toDoItemViewData.isDone,
                    0
                )
            )
        }
    }

    fun onItemAddClicked(textToAdd: String) {
        AsyncTask.execute {
            repository.addToDo(textToAdd)
        }
    }
}