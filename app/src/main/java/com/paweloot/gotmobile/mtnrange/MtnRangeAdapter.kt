package com.paweloot.gotmobile.mtnrange

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.databinding.ListItemMtnRangeBinding
import com.paweloot.gotmobile.model.MtnRange

class MtnRangeAdapter(private val mtnRanges: List<MtnRange>) :
    RecyclerView.Adapter<MtnRangeAdapter.MtnRangeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MtnRangeHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMtnRangeBinding.inflate(inflater, parent, false)

        return MtnRangeHolder(binding)
    }

    override fun onBindViewHolder(holder: MtnRangeHolder, position: Int) {
        holder.binding.mtnRange = mtnRanges[position]
    }

    override fun getItemCount() = mtnRanges.size

    inner class MtnRangeHolder(val binding: ListItemMtnRangeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}