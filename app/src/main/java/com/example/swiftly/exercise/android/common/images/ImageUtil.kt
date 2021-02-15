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

package com.example.swiftly.exercise.android.common.images

import android.widget.ImageView
import com.example.swiftly.exercise.android.R
import com.example.swiftly.exercise.android.common.displaymodel.NetworkImageModel
import com.squareup.picasso.Picasso

/**
 * Loads an image into an ImageView according to the given [model].
 */
fun ImageView.setNetworkImage(model: NetworkImageModel) {
    contentDescription = model.altText

    Picasso.get()
        .load(model.url)
        .placeholder(model.placeholderResId ?: DEFAULT_PLACEHOLDER_RES_ID)
        .into(this)
}

private const val DEFAULT_PLACEHOLDER_RES_ID = R.mipmap.ic_launcher