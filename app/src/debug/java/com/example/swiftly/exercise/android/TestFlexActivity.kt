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

package com.example.swiftly.exercise.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.swiftly.exercise.android.common.displaymodel.AspectRatioParams
import com.example.swiftly.exercise.android.common.displaymodel.NetworkImageModel
import com.example.swiftly.exercise.android.common.util.resString
import com.example.swiftly.exercise.android.specials.displaymodel.DiscountedProductTileDisplayModel
import com.example.swiftly.exercise.android.specials.view.SpecialsAdapter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FixedFlexboxItemDecoration
import com.google.android.flexbox.FixedFlexboxLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxItemDecoration.HORIZONTAL
import com.google.android.flexbox.JustifyContent

class TestFlexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_flex)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = SpecialsAdapter(this, R.string.managers_special.resString())
        adapter.canvasUnit = 16

        recyclerView.also {
            it.layoutManager = FixedFlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP).apply {
                justifyContent = JustifyContent.CENTER
                alignItems = AlignItems.CENTER
            }

            it.adapter = adapter

            val dividersDecoration = FixedFlexboxItemDecoration(this).apply {
                setDrawable(ContextCompat.getDrawable(this@TestFlexActivity, R.drawable.divider_8w_16h))
                setOrientation(HORIZONTAL)
            }
            it.addItemDecoration(dividersDecoration)
        }

        val image = NetworkImageModel(
            url = "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
            placeholderResId = R.mipmap.ic_launcher,
            altText = "example image"
        )

        adapter.data = listOf(
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(8, 8)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title that wraps to two lines",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(8, 6)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(16, 8)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title that wraps to two lines",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(8, 6)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(16, 8)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title that wraps to two lines",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(8, 6)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(16, 8)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title that wraps to two lines",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(8, 6)
            ),
            DiscountedProductTileDisplayModel(
                imageModel = image,
                title = "This is an example title",
                formattedPrice = "\$888.88",
                originalFormattedPrice = "\$999.99",
                aspectRatio = AspectRatioParams(16, 8)
            )
        )
    }

}