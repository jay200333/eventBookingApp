// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
    kotlin("jvm") version "1.9.25"
    alias(libs.plugins.jetbrainsKotlinPluginSerialization) apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false // For Hilt
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
}