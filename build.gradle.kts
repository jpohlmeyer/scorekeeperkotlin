// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
//    repositories {
//        gradlePluginPortal()
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath("com.android.tools.build:gradle:9.4.0")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.60.1")
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.9.8")
//    }
//}

plugins {
//    id("com.android.application") version "9.4.0" apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLegacyKapt) apply false
    alias(libs.plugins.daggerHilt) apply false
    alias(libs.plugins.navigation) apply false
}
