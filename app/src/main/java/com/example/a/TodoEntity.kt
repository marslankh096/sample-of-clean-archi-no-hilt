package com.example.a

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "arslantbl")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val isDone: Boolean = false
)
