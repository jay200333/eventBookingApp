package com.example.eventbookingapp.view.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.eventbookingapp.HomeScreenRoute
import com.example.eventbookingapp.MapScreenRoute
import com.example.eventbookingapp.MyPageScreenRoute
import com.example.eventbookingapp.R
import com.example.eventbookingapp.ScreenRouter
import com.example.eventbookingapp.view.main.components.BottomNavIcon

@Composable
fun MainScreen() {
    val bottomNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val navList = listOf(
                HomeScreenRoute, MapScreenRoute, MyPageScreenRoute
            )

            BottomNavigation {
                navList.forEach { nav ->
                    val selected = currentDestination?.hasRoute(nav::class) ?: false
                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            bottomNavController.navigate(nav) {
                                // 내비게이션 시작 지점까지 있는 모든 스택을 팝하는 부분
                                // 탭한 스크린만 남겨놓기 위한 설계
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    // 화면 성태 저장하는 부분
                                    saveState = true
                                }
                                // 같은 탭을 또 클릭했을 때 중복으로 스택에 쌓이지 않도록 하는 설정
                                launchSingleTop = true
                                // 같은 탭을 또 클릭했을 때 상태 유지를 위한 설정
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = nav.iconId),
                                tint = if (selected) Color.Black else Color.White,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    ) { defaultPadding ->
        val padding = defaultPadding

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = bottomNavController,
            startDestination = HomeScreenRoute
        ) {
            composable<HomeScreenRoute> {
                HomeScreen()
            }
            composable<MapScreenRoute> {
                MapScreen()
            }
            composable<MyPageScreenRoute> {
                MyPageScreen()
            }
        }
    }
}