/*
 * Copyright (c) 2021 Jonathan Schooler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId("com.example.swiftly.exercise.android")
        versionCode = 1
        versionName = "1.0"

        minSdkVersion(23)
        targetSdkVersion(30)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    // Kotlin
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Coroutines.coroutinesAndroid)

    // Jetpack
    implementation(Dependencies.AndroidX.AppCompat.appCompat)
    implementation(Dependencies.AndroidX.ConstraintLayout.constraintLayout)
    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation(Dependencies.AndroidX.Fragment.fragmentKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleLiveDataKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleViewModelKtx)
    implementation(Dependencies.AndroidX.SwipeRefresh.swipeRefreshLayout)

    // Google
    implementation(Dependencies.Google.Material.material)
    implementation(Dependencies.Google.Flexbox.flexbox)

    // Square
    implementation(Dependencies.Moshi.moshi)
    kapt(Dependencies.Moshi.moshiCodeGen)
    implementation(Dependencies.Libs.okHttp)
    implementation(Dependencies.Libs.picasso)

    // test
    testImplementation(Dependencies.Junit.jUnit)
    testImplementation(Dependencies.AndroidX.Test.testExtJunit)
    testImplementation(Dependencies.Robolectric.robolectric)
}