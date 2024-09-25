package com.example.eventbookingapp.view.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView


@Composable
fun MapScreen() {
    val context = LocalContext.current

    Surface {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val mapView = MapView(context)
                mapView.apply {
                    mapView.start(object : MapLifeCycleCallback() {
                        override fun onMapDestroy() {
                            // 지도 API 가 정상적으로 종료될 때 호출됨
                        }

                        override fun onMapError(error: Exception) {
                            println(error)
                        }
                    }, object : KakaoMapReadyCallback() {
                        override fun onMapReady(kakaoMap: KakaoMap) {
                            println("It's ready")
                        }
                    })
                }
            },
            update = { mapView ->

            }
        )
    }
}