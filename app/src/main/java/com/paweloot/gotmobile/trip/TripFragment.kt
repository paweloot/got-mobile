package com.paweloot.gotmobile.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.databinding.FragmentTripBinding

class TripFragment : Fragment(), PointAdapter.OnPointClickListener {

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

        setUpBindingVariables()
        setUpPointList()
        observeCurrentState()

        return binding.root
    }

    private fun setUpBindingVariables() {
        val currMtnRange = appViewModel.mtnRange.value
        val currMtnGroup = appViewModel.mtnGroup.value

        binding.mtnRange = currMtnRange
        binding.mtnGroup = currMtnGroup
    }

    private fun setUpPointList() {
        binding.pointList.layoutManager = LinearLayoutManager(context)
        binding.pointList.adapter = PointAdapter(this)

        viewModel.getPoints().observe(this, Observer { points ->
            (binding.pointList.adapter as PointAdapter).setData(points)
        })
    }

    private fun observeCurrentState() {
        viewModel.currentState.observe(this, Observer { state ->

            when (state) {
                SELECT_START_POINT -> {
                    binding.pointListLabel.text =
                        getString(R.string.start_point_label)
                }
                SELECT_END_POINT -> {
                    binding.pointListLabel.text =
                        getString(R.string.end_point_label)
                }
                SELECT_VIA_POINT -> {
                    binding.pointListLabel.text =
                        getString(R.string.via_point_label)
                }
                POINTS_SELECTED -> {
                    binding.pointList.visibility = View.GONE
                    binding.pointListLabel.visibility = View.GONE
                }
            }
        })
    }

    override fun onClick(v: View?) {

        val pointName: TextView? = v?.findViewById(R.id.point_name)

        when (viewModel.currentState.value) {
            SELECT_START_POINT -> {
                binding.startInput.setText(pointName?.text)
                viewModel.currentState.value = SELECT_END_POINT
            }
            SELECT_END_POINT -> {
                binding.endInput.setText(pointName?.text)
                viewModel.currentState.value = POINTS_SELECTED
            }
        }

    }
}
