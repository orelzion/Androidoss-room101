package com.example.room101

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.room101.model.ToDoRepository
import com.example.room101.model.db.ToDoAppDb
import com.example.room101.view.ToDoAdapter
import com.example.room101.viewmodel.ToDoItemViewData
import com.example.room101.viewmodel.ToDoViewModel
import com.example.room101.viewmodel.ToDoViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ToDoViewModel> {
        val toDoDao = Room.databaseBuilder(this, ToDoAppDb::class.java, "to-do")
                .build()
                .getToDoDao()
        ToDoViewModelFactory(ToDoRepository(toDoDao))
    }

    private val toDoListAdapter by lazy { ToDoAdapter(this::onTodoChanged) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoListView: RecyclerView = findViewById(R.id.toDoList)
        toDoListView.adapter = toDoListAdapter

        viewModel.listLiveData.observe(this) { viewState ->
            toDoListAdapter.submitList(viewState.itemsList)
        }

        val addItemEditText: EditText = findViewById(R.id.addItemEditText)
        val addItemBtn: Button = findViewById(R.id.addItemButton)

        addItemBtn.setOnClickListener {
            viewModel.onItemAddClicked(addItemEditText.text.toString())
            addItemEditText.text = null
        }
    }

    private fun onTodoChanged(toDoItemViewData: ToDoItemViewData) {
        viewModel.onTodoChanged(toDoItemViewData)
    }
}