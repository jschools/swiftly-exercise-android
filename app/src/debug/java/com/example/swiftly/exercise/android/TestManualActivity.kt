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

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class TestManualActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_manual)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = ManualAdapter(this)


    }

    private class ManualAdapter(
        private val context: Context
    ) : RecyclerView.Adapter<ManualAdapter.RowHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        class RowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

    }

}