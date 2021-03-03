package com.example.room101.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val text: String,
    val isDone: Boolean = false
)
