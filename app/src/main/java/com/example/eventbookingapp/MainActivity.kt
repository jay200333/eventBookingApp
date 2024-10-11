package com.example.eventbookingapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventbookingapp.config.exceptions.IllegalPermissionException
import com.example.eventbookingapp.config.exceptions.NotFoundPermissionException
import com.example.eventbookingapp.module.LocationModuleInterface
import com.example.eventbookingapp.view.main.MainScreen
import com.example.eventbookingapp.ui.theme.EventBookingAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import javax.inject.Inject

// Hilt 사용 Annotation
// 해당 어노테이션이 있으면 하위 레벨에서 종속 항목을 가져오는 것이 가능합니다.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var locationModule: LocationModuleInterface
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        /*
        권한 요청 완료 후 실행되는 콜백. 각 키에 맞는 값이 true 값이면 해당 로직 실행
         */
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                locationModule.syncCurrentLocation(fusedLocationProviderClient)
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                locationModule.syncCurrentLocation(fusedLocationProviderClient)
            }
            else -> {
                throw NotFoundPermissionException("수락된 권한이 없습니다.")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            // Compose 최상단에서 앱의 테마를 결정하는 컴포저블 함수
            EventBookingAppTheme {
                // A surface container using the 'background' color from the theme
                // 앱 전체적인 NavHost에 관여해야 하므로 NavHost와 같은 레벨에 선언되어야 한다.
                val navController = rememberNavController()

                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = HomeScreenRoute
                ) {
                    composable<HomeScreenRoute> {
                        MainScreen(
                            fusedLocationProviderClient,
                            locationPermissionLauncher
                        )
                    }
                }
            }
        }
    }
}