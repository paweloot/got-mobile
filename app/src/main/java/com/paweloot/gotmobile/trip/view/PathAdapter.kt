package com.paweloot.gotmobile.trip.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.model.entity.SummaryPath

class PathAdapter(private val summaryPaths: List<SummaryPath>) :
    RecyclerView.Adapter<PathAdapter.PathHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_summary_path, parent, false)

        return PathHolder(view)
    }

    override fun onBindViewHolder(holder: PathHolder, position: Int) {
        val summaryPath = summaryPaths[position]

        val path = "${summaryPath.from} - ${summaryPath.to}"
        holder.fromTo.text = path
        holder.points.text = summaryPath.points.toString()
    }

    override fun getItemCount() = summaryPaths.size

    inner class PathHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        val fromTo: TextView = view.findViewById(R.id.from_to)
        val points: TextView = view.findViewById(R.id.points)
    }
}