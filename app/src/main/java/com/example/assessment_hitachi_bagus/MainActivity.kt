package com.example.assessment_hitachi_bagus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "search"
            ) {
                composable("search") {
                    SearchScreen(
                        viewModel = viewModel,
                        onUserClick = { login ->
                            navController.navigate("detail/$login")
                        }
                    )
                }
                composable(
                    "detail/{login}",
                    arguments = listOf(navArgument("login") { type = NavType.StringType })
                ) { backStackEntry ->
                    val login = backStackEntry.arguments?.getString("login") ?: return@composable
                    UserDetailScreen(
                        login = login,
                        db = db,
                        onBack = { navController.popBackStack() }
                    )
                }

            }
//            SearchScreen(viewModel)
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