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

package com.example.swiftly.exercise.android.specials.data

import com.example.swiftly.exercise.android.R
import com.example.swiftly.exercise.android.common.data.ErrorResult
import com.example.swiftly.exercise.android.common.data.JsonUtil
import com.example.swiftly.exercise.android.common.data.LoadError
import com.example.swiftly.exercise.android.common.data.LoadResult
import com.example.swiftly.exercise.android.common.data.SuccessResult
import com.example.swiftly.exercise.android.common.data.parseJsonOrThrow
import com.example.swiftly.exercise.android.common.util.logd
import com.example.swiftly.exercise.android.common.util.resString
import com.example.swiftly.exercise.android.specials.json.SpecialsJson
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.source

object SpecialsService {

    private val okHttpClient: OkHttpClient by lazy { OkHttpClient() }

    suspend fun fetchSpecialsJson(): LoadResult<SpecialsJson> {
        // build request
        val request = Request.Builder()
            .url(URL_SPECIALS)
            .build()

        // execute call and parse on IO dispatcher
        @Suppress("BlockingMethodInNonBlockingContext")
        return withContext(Dispatchers.IO) {
            try {
                // execute request
                val response = okHttpClient.newCall(request).execute()

                logd { "Executing service call" }

                // if body came back, attempt to parse
                response.body?.byteStream()?.let {
                    SuccessResult(it.parseJsonOrThrow())
                } ?: ErrorResult(LoadError("null response body", null))
            } catch (t: Throwable) {
                ErrorResult(
                    error = LoadError(
                        technicalMessage = t.message,
                        displayMessage = R.string.error_generic_display.resString()
                    )
                )
            }
        }
    }

    private const val URL_SPECIALS = "https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/backup"
}