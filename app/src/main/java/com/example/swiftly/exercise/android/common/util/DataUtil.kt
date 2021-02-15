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

import com.example.swiftly.exercise.android.common.data.InProgressResult
import com.example.swiftly.exercise.android.common.data.LoadResult
import com.example.swiftly.exercise.android.common.data.mapSuccessCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataUtil {

    /**
     * Helper for building flows that fetch some data and transform it, emitting status events such
     * as [InProgressResult], [SuccessResult], and [ErrorResult] as necessary.
     */
    fun <X, Y> fetchAndTransformFlow(fetch: suspend () -> LoadResult<X>, transform: suspend (X) -> Y): Flow<LoadResult<Y>> {
        return flow {
            // in progress
            emit(InProgressResult())

            // fetch
            emit(fetch())
        }.mapSuccessCatching {
            transform(it)
        }
    }

    /**
     * The duration, in milliseconds, that a [SharedFlow] should wait before cancelling its coroutine after
     * all observers have been destroyed.
     */
    const val ACTIVITY_STOPPED_DATA_STALE_DURATION_MS = 1_000L
}