//package com.example.a.insert
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.a.insert.InsertTodoUseCase
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class TodoViewModel(
//    private val getTodosUseCase: GetTodosUseCase,
//    private val insertTodoUseCase: InsertTodoUseCase
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(TodoState())
//    val state: StateFlow<TodoState> = _state
//
//    init {
//        loadTodos()
//    }
//
//    private fun loadTodos() {
//        viewModelScope.launch {
//            getTodosUseCase().collect { list ->
//                _state.value = _state.value.copy(todos = list)
//            }
//        }
//    }
//
//    fun insertStatic() {
//        viewModelScope.launch {
//            insertTodoUseCase(
//                Todo(
//                    id = 0,
//                    title = "name1"
//                )
//            )
//        }
//    }
//}
