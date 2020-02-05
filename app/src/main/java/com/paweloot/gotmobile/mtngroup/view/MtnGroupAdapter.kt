package com.paweloot.gotmobile.mtngroup.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.databinding.ListItemMtnGroupBinding
import com.paweloot.gotmobile.model.entity.MtnGroup

class MtnGroupAdapter(private val clickListener: OnMtnGroupClickListener) :
    RecyclerView.Adapter<MtnGroupAdapter.MtnGroupHolder>() {

    private var mtnGroups: List<MtnGroup> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MtnGroupHolder {

        val binding =
            ListItemMtnGroupBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        return MtnGroupHolder(binding)
    }

    override fun onBindViewHolder(holder: MtnGroupHolder, position: Int) {
        val mtnGroup = mtnGroups[position]

        holder.binding.mtnGroup = mtnGroup
        holder.binding.root.setOnClickListener {
            clickListener.onMtnGroupClicked(mtnGroup)
        }
    }

    override fun getItemCount() = mtnGroups.size

    fun setMtnGroups(mtnGroups: List<MtnGroup>) {
        this.mtnGroups = mtnGroups
        notifyDataSetChanged()
    }

    inner class MtnGroupHolder(val binding: ListItemMtnGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    interface OnMtnGroupClickListener {
        fun onMtnGroupClicked(mtnGroup: MtnGroup)
    }
}