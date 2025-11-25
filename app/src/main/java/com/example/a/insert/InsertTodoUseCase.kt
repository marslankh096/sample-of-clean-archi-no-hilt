package com.example.a.insert

import com.example.a.Todo
import com.example.a.TodoRepository

class InsertTodoUseCase(
    private val repository: TodoRepository   // ✅ CORRECT
) {
    suspend operator fun invoke(todo: Todo) {
        repository.insertTodo(todo)          // ✅ NOW WORKS
    }
}
