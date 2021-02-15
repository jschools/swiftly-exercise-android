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

package com.example.swiftly.exercise.android.specials.logic

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.swiftly.exercise.android.common.data.parseJsonOrThrow
import com.example.swiftly.exercise.android.specials.json.SpecialsJson
import com.example.swiftly.exercise.android.test.TestUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class SpecialsConverterTest {

    @Test
    fun parseSpecials_simple() {
        // parse the test JSON
        val json = TestUtil.openResourceStream("specials/specials_simple.json").parseJsonOrThrow<SpecialsJson>()
        val displayModel = SpecialsConverter.convertSpecials(json)

        // check root-level properties
        assertEquals(16, displayModel.canvasUnit)
        assertEquals(3, displayModel.specials.size)

        // check specials content
        displayModel.specials[0].also {
            assertEquals("Test Display Name #1", it.title)
            assertEquals("\$1.23", it.formattedPrice)
            assertEquals("\$2.00", it.originalFormattedPrice)
            assertEquals(16, it.aspectRatio.width)
            assertEquals(8, it.aspectRatio.height)
            assertEquals("Test Display Name #1 product image", it.imageModel.altText)
        }

        // TODO check other specials
    }
}