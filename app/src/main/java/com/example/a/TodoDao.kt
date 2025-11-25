package com.example.a
 
 

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM arslantbl")
    fun getAll(): Flow<List<TodoEntity>>


    @Insert
    suspend fun insert(todo: TodoEntity)   // ✅ added
}
