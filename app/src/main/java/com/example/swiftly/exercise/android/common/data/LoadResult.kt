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

import com.example.swiftly.exercise.android.R
import com.example.swiftly.exercise.android.common.util.resString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Base wrapper for data that can be in various states of loading.
 */
sealed class LoadResult<out T> {
    /**
     * The data that was loaded, if it was loaded. Otherwise returns `null`.
     */
    open val successfulData: T? = null
}

/**
 * Represents data that loaded successfully.
 */
class SuccessResult<T>(
    /**
     * Since this subclass indicates a successful load, the data is never `null`
     */
    val data: T
) : LoadResult<T>() {
    override val successfulData: T?
        get() = data
}

/**
 * Indicates the load is in progress.
 */
class InProgressResult<T> : LoadResult<T>()

/**
 * Indicates the load has stopped because of an error.
 */
class ErrorResult<T>(
    val error: LoadError
) : LoadResult<T>()

/**
 * Description of a error that occurred during loading
 */
class LoadError(
    /**
     * A message that is not suitable for showing to the user, but may assist in debugging the error.
     */
    val technicalMessage: String?,

    /**
     * A message suitable for showing to the user that communicates a general cause for the error.
     */
    val displayMessage: String?
)

/**
 * Transforms the values in a flow. [SuccessResult]s will be processed with the given [transform], and
 * any exception will be caught and converted into an [ErrorResult]. Other [LoadResult]s will be passed
 * through directly.
 */
fun <X, Y> Flow<LoadResult<X>>.mapSuccessCatching(transform: suspend (X) -> Y): Flow<LoadResult<Y>> {
    return mapSuccess {
        SuccessResult(transform(it))
    }.catch {
        ErrorResult<Y>(
            error = LoadError(
                technicalMessage = "Caught ${it.javaClass.name}: ${it.message}",
                displayMessage = R.string.error_generic_display.resString()
            )
        )
    }
}

/**
 * Transforms the successful values in a flow with the given [transform] block. Other [LoadResult]s will
 * be passed through directly.
 */
fun <X, Y> Flow<LoadResult<X>>.mapSuccess(transform: suspend (X) -> LoadResult<Y>): Flow<LoadResult<Y>> {
    return map { it.letSuccess(transform) }
}

/**
 * Transforms a single [LoadResult]. If it is a [SuccessResult], the given [transform] will be applied.
 * Otherwise, the result type will be passed through.
 */
suspend fun <X, Y> LoadResult<X>.letSuccess(transform: suspend (X) -> LoadResult<Y>): LoadResult<Y> {
    return when (this) {
        is SuccessResult -> transform(data)
        is InProgressResult -> InProgressResult()
        is ErrorResult -> ErrorResult(this.error)
    }
}