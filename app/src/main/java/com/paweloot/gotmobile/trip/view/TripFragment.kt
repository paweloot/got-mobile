package com.paweloot.gotmobile.trip.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.databinding.FragmentTripBinding
import com.paweloot.gotmobile.model.entity.Point
import com.paweloot.gotmobile.trip.*
import java.util.*

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
        setHasOptionsMenu(true)

        binding = FragmentTripBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pointsFragment =
            PointsFragment(viewModel, appViewModel)
        requireFragmentManager().beginTransaction()
            .add(R.id.fragment_container, pointsFragment)
            .commit()

        setUpBindingVariables()
        observeSelectedPoint()
        observeCurrentState()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.trip_menu, menu)
    }

    private fun setUpBindingVariables() {
        val currMtnRange = appViewModel.mtnRange.value
        val currMtnGroup = appViewModel.mtnGroup.value

        binding.mtnRange = currMtnRange
        binding.mtnGroup = currMtnGroup
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_via_point -> {
                if (viewModel.currentState.value == SELECT_END_POINT) {
                    viewModel.currentState.value = SELECT_VIA_POINT
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeSelectedPoint() {
        viewModel.selectedPoint.observe(this, Observer { point ->

            when (viewModel.currentState.value) {
                SELECT_START_POINT -> {
                    binding.startInput.setText(point.name)

                    addPathPoint(point)
                    setCurrentState(SELECT_END_POINT)

                    filterPoints()
                }
                SELECT_VIA_POINT -> {
                    binding.startInput.setText(point.name)

                    addPathPoint(point)
                    setCurrentState(SELECT_END_POINT)

                    filterPoints()
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
                SELECT_VIA_POINT -> {
                    binding.startInput.text = null
                    binding.startInputLayout.hint = getString(R.string.via_input_text_hint)
                }
                POINTS_SELECTED -> {
                    val summaryFragment =
                        SummaryFragment(
                            viewModel
                        )

                    requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, summaryFragment)
                        .commit()
                }
                SAVE_TRIP -> {
                    val loggedTourist = appViewModel.loggedTourist.value!!

                    showDatePickerDialog { view, year, month, dayOfMonth ->
                        val calendar = Calendar.getInstance()
                        calendar.set(year, month, dayOfMonth)
                        val selectedDate = calendar.time

                        viewModel.saveTrip(
                            loggedTourist,
                            selectedDate,
                            this::onTripSavedCallback
                        )
                    }
                }
            }
        })
    }

    private fun onTripSavedCallback(success: Boolean) {
        val dialog = MessageDialogFragment(success)
        dialog.show(requireFragmentManager(), null)
    }

    private fun showDatePickerDialog(
        onDateSetCallback: (
            datePicker: DatePicker,
            year: Int,
            month: Int,
            dayOfMonth: Int
        ) -> Unit
    ) {
        Calendar.getInstance().also { c ->
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                onDateSetCallback,
                year, month, dayOfMonth
            )

            datePickerDialog.datePicker.minDate = Calendar.getInstance().timeInMillis
            datePickerDialog.show()
        }
    }

    private fun addPathPoint(point: Point) {
        viewModel.addPathPoint(point)
    }

    private fun setCurrentState(state: Int) {
        viewModel.currentState.value = state
    }

    private fun filterPoints() {
        viewModel.filterPoints()
    }
}
