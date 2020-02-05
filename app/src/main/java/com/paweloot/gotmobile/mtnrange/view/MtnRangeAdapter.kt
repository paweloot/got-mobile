package com.paweloot.gotmobile.mtnrange.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.databinding.ListItemMtnRangeBinding
import com.paweloot.gotmobile.model.entity.MtnRange

class MtnRangeAdapter(private val clickListener: OnMtnRangeClickedListener) :
    RecyclerView.Adapter<MtnRangeAdapter.MtnRangeHolder>() {

    private var mtnRanges: List<MtnRange> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MtnRangeHolder {

        val binding =
            ListItemMtnRangeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        return MtnRangeHolder(binding)
    }

    override fun onBindViewHolder(holder: MtnRangeHolder, position: Int) {
        val mtnRange = mtnRanges[position]

        holder.binding.mtnRange = mtnRange
        holder.binding.root.setOnClickListener {
            clickListener.onMtnRangeClicked(mtnRange)
        }
    }

    override fun getItemCount() = mtnRanges.size

    fun setMtnRanges(mtnRanges: List<MtnRange>) {
        this.mtnRanges = mtnRanges
        notifyDataSetChanged()
    }

    inner class MtnRangeHolder(val binding: ListItemMtnRangeBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    interface OnMtnRangeClickedListener {
        fun onMtnRangeClicked(mtnRange: MtnRange)
    }
}