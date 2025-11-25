package com.example.a


data class TodoState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList()
)
