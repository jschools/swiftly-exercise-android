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

package com.example.swiftly.exercise.android.common.util

import android.util.Log
import com.example.swiftly.exercise.android.BuildConfig
import com.example.swiftly.exercise.android.SwiftlyApplication

/**
 * Gets an Android resource String
 */
fun Int.resString() = SwiftlyApplication.instance.getString(this)

/**
 * Gets and Android resource string that takes format arguments
 */
fun Int.resString(vararg args: String) = SwiftlyApplication.instance.getString(this, *args)

/**
 * Utility for quickly logging something to LogCat using DEBUG level. This does nothing
 * when [BuildConfig.DEBUG] is `true`.
 */
fun logd(logBlock: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d(LOG_TAG, logBlock())
    }
}

private const val LOG_TAG = "Swiftly"