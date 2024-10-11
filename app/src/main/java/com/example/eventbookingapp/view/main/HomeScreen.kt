package com.example.eventbookingapp.view.main

import android.Manifest
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventbookingapp.view.main.viewmodel.HomeScreenViewModel
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun HomeScreen(
    client: FusedLocationProviderClient,
    locationPermissionLauncher: ActivityResultLauncher<Array<String>>,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        val innerPadding = padding
        val context: Context = LocalContext.current
        val activity = context as? ComponentActivity
        val currentLocation = viewModel.currentLocation.collectAsState()

        Column {
            Button(
                onClick = {
                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    )
                }
            ) {
                Text(text = "위치 권한 요청")
            }

            Text(text = "${currentLocation.value?.toString()}")
        }
    }
}