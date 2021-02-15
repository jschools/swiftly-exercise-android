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

@Suppress("MemberVisibilityCanBePrivate")
object Dependencies {

    object AndroidX {
        object AppCompat {
            const val version = "1.2.0"

            const val appCompat = "androidx.appcompat:appcompat:$version"
        }

        object ConstraintLayout {
            const val version = "2.0.4"

            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Core {
            const val version = "1.3.2"

            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object Fragment {
            const val version = "1.3.0"

            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            const val version = "2.3.0"

            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object SwipeRefresh {
            const val version = "1.1.0"

            const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$version"
        }

        object Test {
            const val testExtJunit = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Coroutines {
        const val version = "1.4.2"

        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Google {
        object Material {
            const val version = "1.2.1"

            const val material = "com.google.android.material:material:$version"
        }

        object Flexbox {
            const val version = "2.0.1"

            const val flexbox = "com.google.android:flexbox:$version"
        }
    }

    object GradleAndroid {
        const val version = "4.1.2"

        const val androidGradlePlugin = "com.android.tools.build:gradle:$version"
    }

    object Junit {
        const val version = "4.+"

        const val jUnit = "junit:junit:$version"
    }

    object Kotlin {
        const val version = "1.4.30"

        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Moshi {
        const val version = "1.11.0"

        const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Libs {
        const val picasso = "com.squareup.picasso:picasso:2.71828"
        const val okHttp = "com.squareup.okhttp3:okhttp:4.9.0"
    }

    object Robolectric {
        const val version = "4.5.1"

        const val robolectric = "org.robolectric:robolectric:$version"
    }
}