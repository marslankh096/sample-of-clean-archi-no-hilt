package com.example.a

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a.insert.InsertTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val insertTodoUseCase: InsertTodoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TodoState())
    val state: StateFlow<TodoState> = _state

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            getTodosUseCase().collect { list ->
                _state.value = _state.value.copy(todos = list)
            }
        }
    }

    // 🔥 Insert 3 items: name1, item2, ars2
    fun insertStatic() {
        viewModelScope.launch {

            insertTodoUseCase(
                Todo(
                    id = 0,
                    title = "name1"
                )
            )

            insertTodoUseCase(
                Todo(
                    id = 0,
                    title = "item2"
                )
            )

            insertTodoUseCase(
                Todo(
                    id = 0,
                    title = "ars2"
                )
            )
        }
    }
}
