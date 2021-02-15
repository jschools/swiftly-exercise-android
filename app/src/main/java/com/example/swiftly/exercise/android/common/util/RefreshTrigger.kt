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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

/**
 * Wraps a flow, restarting it when [refresh] is called.
 */
class RefreshTrigger {

    private val stateFlow = MutableStateFlow(0)

    fun refresh() {
        stateFlow.value += 1
    }

    fun <T> wrap(flow: Flow<T>): Flow<T> {
        return stateFlow.transform {
            emitAll(flow)
        }
    }
}