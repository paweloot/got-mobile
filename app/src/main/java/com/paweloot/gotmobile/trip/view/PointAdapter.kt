package com.paweloot.gotmobile.trip.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.model.entity.Point
import com.paweloot.gotmobile.trip.TripViewModel

class PointAdapter(private val viewModel: TripViewModel) :
    RecyclerView.Adapter<PointAdapter.PointHolder>() {

    private var points = emptyList<Point>()

    fun setData(data: List<Point>) {
        points = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_point, parent, false)

        return PointHolder(view)
    }

    override fun onBindViewHolder(holder: PointHolder, position: Int) {
        holder.bindPoint(points[position])
    }

    override fun getItemCount() = points.size

    inner class PointHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private val pointName: TextView = view.findViewById(R.id.point_name)

        fun bindPoint(point: Point) {
            pointName.text = point.name

            view.setOnClickListener {
                viewModel.setSelectedPoint(point)
            }
        }
    }
}