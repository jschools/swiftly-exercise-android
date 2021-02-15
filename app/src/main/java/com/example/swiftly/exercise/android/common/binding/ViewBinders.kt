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

package com.example.swiftly.exercise.android.common.binding

import android.widget.ImageView
import android.widget.TextView
import android.graphics.Paint
import androidx.databinding.BindingAdapter
import com.example.swiftly.exercise.android.common.displaymodel.AspectRatioParams
import com.example.swiftly.exercise.android.common.displaymodel.NetworkImageModel
import com.example.swiftly.exercise.android.common.images.setNetworkImage
import com.example.swiftly.exercise.android.common.view.FixedWidthAspectRatioFrameLayout

@BindingAdapter("imageModel")
fun ImageView.setImageModel(imageModel: NetworkImageModel) {
    setNetworkImage(imageModel)
}

@BindingAdapter("strikethrough")
fun TextView.setStrikethrough(strikethroughEnabled: Boolean) {
    paintFlags = if (strikethroughEnabled) {
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("aspectRatio")
fun FixedWidthAspectRatioFrameLayout.setAspectRatio(aspectRatio: AspectRatioParams) {
    aspectRatioWidth = aspectRatio.width
    aspectRatioHeight = aspectRatio.height
}