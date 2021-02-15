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

import com.example.swiftly.exercise.android.R
import com.example.swiftly.exercise.android.common.displaymodel.AspectRatioParams
import com.example.swiftly.exercise.android.common.displaymodel.NetworkImageModel
import com.example.swiftly.exercise.android.common.util.logd
import com.example.swiftly.exercise.android.common.util.resString
import com.example.swiftly.exercise.android.specials.displaymodel.DiscountedProductTileDisplayModel
import com.example.swiftly.exercise.android.specials.displaymodel.SpecialsDisplayModel
import com.example.swiftly.exercise.android.specials.json.SpecialJson
import com.example.swiftly.exercise.android.specials.json.SpecialsJson

object SpecialsConverter {

    /**
     * Converts the JSON from the Manager's Specials service to a display model for the screen.
     * @throws [Throwable] if the JSON is missing any required fields or otherwise unparseable.
     */
    fun convertSpecials(json: SpecialsJson): SpecialsDisplayModel {
        return SpecialsDisplayModel(
            canvasUnit = json.canvasUnit!!,
            specials = convertSpecialsList(json.managerSpecials!!)
        )
    }

    private fun convertSpecialsList(jsonList: List<SpecialJson>): List<DiscountedProductTileDisplayModel> {
        return jsonList.mapNotNull {
            try {
                convertSpecial(it)
            } catch (t: Throwable) {
                logd { "Dropping invalid product JSON: ${t.message}" }
                null
            }
        }
    }

    private fun convertSpecial(json: SpecialJson): DiscountedProductTileDisplayModel {
        return DiscountedProductTileDisplayModel(
            imageModel = NetworkImageModel(
                url = json.imageUrl!!,
                altText = json.displayName?.let { R.string.cd_product_format.resString(it) }
            ),
            title = json.displayName!!,
            formattedPrice = appendDollarSymbolToFormattedPrice(json.price!!),
            originalFormattedPrice = appendDollarSymbolToFormattedPrice(json.originalPrice!!),
            aspectRatio = AspectRatioParams(
                width = json.width!!,
                height = json.height!!
            )
        )
    }

    fun appendDollarSymbolToFormattedPrice(formattedPrice: String): String {
        return R.string.price_dollar_format.resString(formattedPrice)
    }

}