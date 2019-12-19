package com.paweloot.gotmobile.trip.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.model.entity.Path
import com.paweloot.gotmobile.model.repository.PathRepository
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

        showSummary()
    }

    private fun showSummary() {
        val summaryPaths = mutableListOf<SummaryPath>()

        val pathPoints = viewModel.pathPoints
        val paths = PathRepository().paths.value!!

        for (i in 0 until pathPoints.size - 1) {
            val idFrom = pathPoints[i].id
            val idTo = pathPoints[i + 1].id

            val gotPoints = calculateGotPoints(idFrom, idTo, paths)
            val fromName = pathPoints.find { point -> point.id == idFrom }!!.name
            val toName = pathPoints.find { point -> point.id == idTo }!!.name
            summaryPaths.add(
                SummaryPath(
                    "$fromName - $toName",
                    gotPoints
                )
            )
        }

        summary_path_list.layoutManager = LinearLayoutManager(context)
        summary_path_list.adapter =
            PathAdapter(summaryPaths)
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
}

data class SummaryPath(
    val fromTo: String,
    val points: Int
)