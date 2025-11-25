package com.example.a
 

class GetTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke() = repository.getTodos()
}
