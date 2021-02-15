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

package com.example.swiftly.exercise.android.common.data

import com.squareup.moshi.Moshi
import okio.buffer
import okio.source
import java.io.InputStream

object JsonUtil {

    private val moshi: Moshi by lazy { Moshi.Builder().build() }

    /**
     * Parses the given [InputStream] as JSON, mapping it into the given data type [T].
     */
    inline fun <reified T> parseJsonOrThrow(input: InputStream): T {
        return parseJsonOrThrow(input, T::class.java)
    }

    /**
     * Parses the given [InputStream] as JSON, mapping it into the given data type [clazz].
     */
    fun <T> parseJsonOrThrow(input: InputStream, clazz: Class<T>): T {
        return input.source().buffer().use {
            moshi.adapter(clazz).fromJson(it)!!
        }
    }
}

inline fun <reified T> InputStream.parseJsonOrThrow(): T = JsonUtil.parseJsonOrThrow(this)