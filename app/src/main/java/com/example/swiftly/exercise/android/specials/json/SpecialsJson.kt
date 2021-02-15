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

package com.example.swiftly.exercise.android.specials.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SpecialsJson(
    val canvasUnit: Int?,
    val managerSpecials: List<SpecialJson>?
)

@JsonClass(generateAdapter = true)
class SpecialJson(
    @Json(name = "display_name")
    val displayName: String?,
    val height: Int?,
    val imageUrl: String?,
    @Json(name = "original_price")
    val originalPrice: String?,
    val price: String?,
    val width: Int?
)

/*
Example JSON:
{
  "canvasUnit": 16,
  "managerSpecials": [
    {
      "display_name": "Noodle Dish with Roasted Black Bean Sauce",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 16
    },
    ...
  ]
}
 */