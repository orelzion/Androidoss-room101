package com.example.room101.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.room101.model.ToDoRepository
import com.example.room101.model.db.ToDoEntity

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {

    private val listLiveData = MutableLiveData<ToDoListViewData>()
    fun bindListViewData(): LiveData<ToDoListViewData> = listLiveData

    fun onListScreenLoaded() {
        loadAllItems()
    }

    private fun loadAllItems() {
        val viewDataItems = convertToViewData(
            repository.getToDoItems(ToDoRepository.ToDoFilter.ALL)
        )
        listLiveData.postValue(
            ToDoListViewData(viewDataItems)
        )
    }

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
        repository.updateToDo(
            ToDoEntity(
                toDoItemViewData.id,
                toDoItemViewData.text,
                toDoItemViewData.isDone
            )
        )

        loadAllItems()
    }

    fun onItemAddClicked(textToAdd: String) {
        repository.addToDo(textToAdd)
        loadAllItems()
    }
}