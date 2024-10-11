import org.jetbrains.kotlin.gradle.targets.js.npm.importedPackageDir
import java.io.FileInputStream
import java.util.Properties

var properties = Properties()
properties.load(FileInputStream("local.properties"))

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    // For Hilt
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.jetbrainsKotlinPluginSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.eventbookingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eventbookingapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "kakaoNativeAppKey", properties.getProperty("kakaoNativeAppKey"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    // For Robolectric tests.
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    // For Compose Navigation
    implementation(libs.androidx.navigation.compose)

    // 직렬화
    implementation(libs.kotlinx.serialization.json)
    // test code

    // For Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // For Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)
    // test22222

    implementation(libs.retrofit)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material)

    implementation(libs.android)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlin.logging.jvm)
    implementation(libs.slf4j.api)
    implementation(libs.play.services.location)
    implementation(libs.gson)
    implementation(libs.slf4j.simple)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}