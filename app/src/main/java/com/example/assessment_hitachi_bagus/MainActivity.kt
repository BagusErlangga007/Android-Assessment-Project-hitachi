package com.example.assessment_hitachi_bagus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.assessment_hitachi_bagus.model.AppDatabase
import com.example.assessment_hitachi_bagus.ui.theme.Assessment_hitachi_bagusTheme
import com.example.assessment_hitachi_bagus.utils.ApiClient
import com.example.assessment_hitachi_bagus.viewmodel.UserRepository
import com.example.assessment_hitachi_bagus.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "github_users.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val db = AppDatabase.getDatabase(this)
        val repository = UserRepository(ApiClient.api, db.userDao())
        val viewModel = UserViewModel(repository)

        setContent {
            SearchScreen(viewModel)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assessment_hitachi_bagusTheme {
        Greeting("Android")
    }
}