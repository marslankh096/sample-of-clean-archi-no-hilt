package com.example.a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.a.insert.InsertTodoUseCase
import com.example.a.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodoViewModel
    private val adapter = TodoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding inflate ho raha hai
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ArslanLog", "Binding set hogaya")

        // Room Database create ho rahi hai
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
        Log.d("ArslanLog", "Database create hogaya")

        // Repository (data ka bridge) create ho rahi hai
        val repo = TodoRepositoryImpl(db.todoDao())
        Log.d("ArslanLog", "Repository set hogayi")

        // UseCases create karna zaroori hai
        val getTodosUseCase = GetTodosUseCase(repo)
        val insertTodoUseCase = InsertTodoUseCase(repo)
        Log.d("ArslanLog", "UseCases ready")

        // ViewModel ko dono UseCases mil rahe hain
        viewModel = TodoViewModel(getTodosUseCase, insertTodoUseCase)
        Log.d("ArslanLog", "ViewModel create hogaya")

        // RecyclerView setup
        binding.rvTodos.layoutManager = LinearLayoutManager(this)
        binding.rvTodos.adapter = adapter
        Log.d("ArslanLog", "RecyclerView set hogaya")

        // Pehli dafa static item insert karo
        viewModel.insertStatic()
        Log.d("ArslanLog", "Static item insert function call hogaya")

        // Flow observe karo – jab bhi data badlay ga list update hogi
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                Log.d("ArslanLog", "New list aayi: ${state.todos}")
                adapter.submitList(state.todos)
            }
        }
    }
}
