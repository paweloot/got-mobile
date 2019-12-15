package com.paweloot.gotmobile.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.databinding.FragmentTripBinding
import com.paweloot.gotmobile.model.Path
import com.paweloot.gotmobile.model.PathRepository
import com.paweloot.gotmobile.model.Point

class TripFragment : Fragment() {

    private lateinit var binding: FragmentTripBinding

    private val viewModel: TripViewModel by lazy {
        ViewModelProviders.of(this).get(TripViewModel::class.java)
    }

    private val appViewModel: AppViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(AppViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTripBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pointsFragment = PointsFragment(viewModel)
        requireFragmentManager().beginTransaction()
            .add(R.id.fragment_container, pointsFragment)
            .commit()

        setUpBindingVariables()
        observeSelectedPoint()
        observeCurrentState()

        return binding.root
    }

    private fun setUpBindingVariables() {
        val currMtnRange = appViewModel.mtnRange.value
        val currMtnGroup = appViewModel.mtnGroup.value

        binding.mtnRange = currMtnRange
        binding.mtnGroup = currMtnGroup
    }

    private fun observeSelectedPoint() {
        viewModel.selectedPoint.observe(this, Observer { point ->

            when (viewModel.currentState.value) {
                SELECT_START_POINT -> {
                    binding.startInput.setText(point.name)

                    addPathPoint(point)
                    setCurrentState(SELECT_END_POINT)
                }
                SELECT_END_POINT -> {
                    binding.endInput.setText(point.name)

                    addPathPoint(point)
                    setCurrentState(POINTS_SELECTED)
                }
            }
        })
    }

    private fun observeCurrentState() {
        viewModel.currentState.observe(this, Observer { state ->
            when (state) {
                POINTS_SELECTED -> {
                    val summaryFragment = SummaryFragment()

                    requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, summaryFragment)
                        .commit()
                }
            }
        })
    }

    private fun showSummary() {
        val pathPoints = viewModel.pathPoints
        val paths = PathRepository().paths.value!!

        for (i in 0 until pathPoints.size - 1) {
            val idFrom = pathPoints[i].id
            val idTo = pathPoints[i + 1].id

            val gotPoints = calculateGotPoints(idFrom, idTo, paths)
        }
    }

    private fun calculateGotPoints(idFrom: Int, idTo: Int, paths: List<Path>): Int {
        for (path in paths) {
            if (path.idFrom == idFrom && path.idTo == idTo)
                return path.gotPoints
            else if (path.idTo == idFrom && path.idFrom == idTo)
                return path.gotPointsReturn
        }

        return 0
    }

    private fun addPathPoint(point: Point) {
        viewModel.addPathPoint(point)
    }

    private fun setCurrentState(state: Int) {
        viewModel.currentState.value = state
    }
}
