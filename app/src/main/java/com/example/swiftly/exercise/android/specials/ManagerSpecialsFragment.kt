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

package com.example.swiftly.exercise.android.specials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.swiftly.exercise.android.R
import com.example.swiftly.exercise.android.common.data.ErrorResult
import com.example.swiftly.exercise.android.common.data.InProgressResult
import com.example.swiftly.exercise.android.common.data.LoadResult
import com.example.swiftly.exercise.android.common.data.SuccessResult
import com.example.swiftly.exercise.android.common.util.DataUtil
import com.example.swiftly.exercise.android.common.util.RefreshTrigger
import com.example.swiftly.exercise.android.common.util.resString
import com.example.swiftly.exercise.android.databinding.FragManagerSpecialsBinding
import com.example.swiftly.exercise.android.specials.data.SpecialsService
import com.example.swiftly.exercise.android.specials.displaymodel.SpecialsDisplayModel
import com.example.swiftly.exercise.android.specials.logic.SpecialsConverter
import com.example.swiftly.exercise.android.specials.view.SpecialsAdapter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FixedFlexboxItemDecoration
import com.google.android.flexbox.FixedFlexboxLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.JustifyContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn

class ManagerSpecialsFragment : Fragment() {

    private val viewModel: ManagerSpecialsViewModel by viewModels()

    class ManagerSpecialsViewModel : ViewModel() {

        private val refreshTrigger = RefreshTrigger()

        val displayModelFlow: StateFlow<LoadResult<SpecialsDisplayModel>> = refreshTrigger.wrap(
            DataUtil.fetchAndTransformFlow(
                fetch = SpecialsService::fetchSpecialsJson,
                transform = SpecialsConverter::convertSpecials
            )
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = DataUtil.ACTIVITY_STOPPED_DATA_STALE_DURATION_MS,
                replayExpirationMillis = 0
            ),
            initialValue = InProgressResult()
        )

        fun requestRefresh() {
            if (displayModelFlow.value !is InProgressResult) {
                refreshTrigger.refresh()
            }
        }
    }

    private var views: Views? = null

    /**
     * All references to Views should be contained in this class. This is to ensure that no View references are
     * leaked in [onDestroyView].
     */
    private class Views(
        val binding: FragManagerSpecialsBinding,
        val adapter: SpecialsAdapter
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // inflate from the binding
        val binding = FragManagerSpecialsBinding.inflate(inflater, container, false).also {
            it.swipeRefresh.setOnRefreshListener {
                viewModel.requestRefresh()
            }
        }

        val context = inflater.context

        views = Views(
            binding = binding,
            adapter = SpecialsAdapter(
                context = context,
                headerText = R.string.managers_special.resString()
            )
        ).also {
            it.binding.recyclerView.apply {
                // create and set LayoutManager
                layoutManager = FixedFlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP).apply {
                    justifyContent = JustifyContent.CENTER
                    alignItems = AlignItems.CENTER
                }

                // set adapter
                adapter = it.adapter

                // add vertical dividers
                val dividersDecoration = FixedFlexboxItemDecoration(context).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_8w_16h))
                    setOrientation(FlexboxItemDecoration.HORIZONTAL)
                }
                addItemDecoration(dividersDecoration)
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.displayModelFlow.collect {
                updateUi(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        views = null
    }

    override fun onStart() {
        super.onStart()

        viewModel.requestRefresh()
    }

    private fun updateUi(result: LoadResult<SpecialsDisplayModel>) {
        val views = views ?: return

        if (result !is InProgressResult) {
            // only hide the refresh view. it shows up automatically.
            views.binding.swipeRefresh.isRefreshing = false
        }

        // update visibilities
        views.binding.error.root.isVisible = result is ErrorResult
        views.binding.recyclerView.isVisible = result is SuccessResult || result is InProgressResult

        // update views
        when (result) {
            is SuccessResult -> {
                // update adapter
                result.data.also {
                    views.adapter.canvasUnit = it.canvasUnit
                    views.adapter.data = it.specials
                }
            }
            is ErrorResult -> {
                // update error message
                result.error.also {
                    views.binding.error.error = it
                }
            }
        }


    }

}