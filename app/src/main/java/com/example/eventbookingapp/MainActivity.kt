package com.example.eventbookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventbookingapp.view.main.MainScreen
import com.example.eventbookingapp.ui.theme.EventBookingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

data class Test(
    val s: String,
    val n: Int
)

// Hilt 사용 Annotation
// 해당 어노테이션이 있으면 하위 레벨에서 종속 항목을 가져오는 것이 가능합니다.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                        MainScreen()
                    }
                }
            }
        }
    }
}