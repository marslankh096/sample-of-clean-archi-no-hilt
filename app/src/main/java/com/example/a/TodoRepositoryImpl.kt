package com.example.a

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> =
        dao.getAll().map { list ->
            list.map {
                Todo(
                    id = it.id,
                    title = it.name,
                    isDone = it.isDone
                )
            }
        }


    override suspend fun insertTodo(todo: Todo) {   // ✅ added
        dao.insert(
            TodoEntity(
                id = 0,
                name = todo.title
            )
        )
    }


}