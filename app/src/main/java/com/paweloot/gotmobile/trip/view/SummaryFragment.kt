package com.paweloot.gotmobile.trip.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.trip.TripViewModel
import kotlinx.android.synthetic.main.fragment_summary.*

class SummaryFragment(private val viewModel: TripViewModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        summary_path_list.layoutManager = LinearLayoutManager(context)

        observeSummaryPaths()
        viewModel.fetchSummaryPaths();
    }

    private fun observeSummaryPaths() {
        viewModel.summaryPaths.observe(this, Observer { summaryPaths ->
            summary_path_list.adapter = PathAdapter(summaryPaths)
        })
    }
}

data class SummaryPath(
    val from: String,
    val to: String,
    val points: Int
)