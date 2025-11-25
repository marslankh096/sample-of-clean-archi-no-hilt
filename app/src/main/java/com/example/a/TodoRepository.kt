package com.example.a
 

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo)   // ✅ added
}
