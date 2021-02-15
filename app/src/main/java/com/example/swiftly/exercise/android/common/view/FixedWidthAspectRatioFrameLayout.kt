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

package com.example.swiftly.exercise.android.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlin.math.roundToInt

/**
 * This helper layout adjusts its height to be a ratio of its width. Use [aspectRatioHeight] and [aspectRatioWidth]
 * to set the aspect ratio. This layout ignores the heightMeasureSpec passed by its parent, so it should always
 * have `android:layout_height="wrap_content"`.
 *
 * This View does respect its [minimumHeight] attribute, so the aspect
 * ratio will be violated when the width is too narrow to allow for this minimum height.
 */
class FixedWidthAspectRatioFrameLayout : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    var aspectRatioWidth: Int = 2
        set(value) {
            field = value
            requestLayout()
        }

    var aspectRatioHeight: Int = 1
        set(value) {
            field = value
            requestLayout()
        }

    private val scalar: Float
        get() = aspectRatioHeight.toFloat() / aspectRatioWidth.toFloat()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // assume incoming width is EXACTLY mode
        val finalWidth = MeasureSpec.getSize(widthMeasureSpec)
        val finalHeight = (finalWidth * scalar).roundToInt().coerceAtLeast(minimumHeight)

        val finalHeightSpec = MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, finalHeightSpec)
    }

}