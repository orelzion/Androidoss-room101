package com.example.room101.viewmodel

data class ToDoListViewData(
    val itemsList: List<ToDoItemViewData> = emptyList()
)

data class ToDoItemViewData(
    val id: Int,
    val text: String,
    val isDone: Boolean
)