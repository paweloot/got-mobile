package com.paweloot.gotmobile.trip.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.trip.SELECT_END_POINT
import com.paweloot.gotmobile.trip.SELECT_START_POINT
import com.paweloot.gotmobile.trip.SELECT_VIA_POINT
import com.paweloot.gotmobile.trip.TripViewModel
import kotlinx.android.synthetic.main.fragment_points.*


class PointsFragment(
    private val viewModel: TripViewModel,
    private val appViewModel: AppViewModel
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_points, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        point_list.layoutManager = LinearLayoutManager(context)
        point_list.adapter =
            PointAdapter(viewModel)

        viewModel.points.observe(this, Observer { points ->
            (point_list.adapter as PointAdapter).setData(points)
        })

        val currentMtnGroup = appViewModel.mtnGroup.value!!
        viewModel.fetchPoints(currentMtnGroup)

        observeCurrentState()
    }

    private fun observeCurrentState() {
        viewModel.currentState.observe(this, Observer { state ->

            when (state) {
                SELECT_START_POINT -> {
                    point_list_label.text =
                        getString(R.string.start_point_label)
                }
                SELECT_END_POINT -> {
                    point_list_label.text =
                        getString(R.string.end_point_label)
                }
                SELECT_VIA_POINT -> {
                    point_list_label.text =
                        getString(R.string.via_point_label)
                }
            }
        })
    }
}
