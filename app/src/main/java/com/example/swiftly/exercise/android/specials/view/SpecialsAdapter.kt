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

package com.example.swiftly.exercise.android.specials.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.swiftly.exercise.android.R
import com.example.swiftly.exercise.android.databinding.ProductTileDiscountedBinding
import com.example.swiftly.exercise.android.databinding.SpecialsHeaderBinding
import com.example.swiftly.exercise.android.specials.displaymodel.DiscountedProductTileDisplayModel
import com.example.swiftly.exercise.android.specials.view.SpecialsAdapter.DiscountedProductTileHolder
import com.google.android.flexbox.FixedFlexboxLayoutManager

class SpecialsAdapter(
    private val context: Context,
    private val headerText: String
) : RecyclerView.Adapter<ViewHolder>() {

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    var data: List<DiscountedProductTileDisplayModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var canvasUnit: Int = 2
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_PRODUCT -> {
                val view = inflater.inflate(R.layout.product_tile_discounted, parent, false)
                DiscountedProductTileHolder(view)
            }
            TYPE_HEADER -> {
                val view = inflater.inflate(R.layout.specials_header, parent, false)
                HeaderHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> {
                holder as HeaderHolder
                holder.binding.headerText = headerText

                val params = holder.itemView.layoutParams as FixedFlexboxLayoutManager.LayoutParams
                params.apply {
                    this.flexBasisPercent = 1f
                }
                holder.itemView.layoutParams = params
            }
            TYPE_PRODUCT -> {
                holder as DiscountedProductTileHolder

                val displayModel = data[position - 1]
                holder.binding.displayModel = displayModel

                val params = holder.itemView.layoutParams as FixedFlexboxLayoutManager.LayoutParams
                params.apply {
                    this.flexBasisPercent = displayModel.aspectRatio.width.toFloat() / canvasUnit
                    this.flexShrink = 1f
                }
                holder.itemView.layoutParams = params
            }
        }
    }

    override fun getItemCount() = data.size + 1 // data + header

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            else -> TYPE_PRODUCT
        }
    }

    class DiscountedProductTileHolder(itemView: View) : ViewHolder(itemView) {
        val binding: ProductTileDiscountedBinding = ProductTileDiscountedBinding.bind(itemView)
    }

    class HeaderHolder(itemView: View) : ViewHolder(itemView) {
        val binding: SpecialsHeaderBinding = SpecialsHeaderBinding.bind(itemView)
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_PRODUCT = 1
    }
}