package com.example.assessment_hitachi_bagus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.assessment_hitachi_bagus.model.AppDatabase
import com.example.assessment_hitachi_bagus.ui.theme.Assessment_hitachi_bagusTheme
import com.example.assessment_hitachi_bagus.utils.GlideImage
import com.example.assessment_hitachi_bagus.viewmodel.UserDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    login: String,
    db: AppDatabase,
    onBack: () -> Unit
) {
    val vm = remember { UserDetailViewModel(db.userDao()) }
    val user by vm.user.collectAsState()

    LaunchedEffect(login) { vm.loadUser(login) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(login) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) { padding ->
        if (user == null) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                GlideImage(
                    imageUrl = user!!.avatarUrl,
                    modifier = Modifier.size(500.dp).clip(CircleShape)
                )
                Spacer(Modifier.height(16.dp))
                Text(user!!.login, style = MaterialTheme.typography.titleLarge)
                Text(user!!.htmlUrl, color = MaterialTheme.colorScheme.primary)
            }
        }
    }

}


