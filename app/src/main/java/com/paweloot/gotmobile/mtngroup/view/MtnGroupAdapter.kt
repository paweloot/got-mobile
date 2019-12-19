package com.paweloot.gotmobile.mtngroup.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.databinding.ListItemMtnGroupBinding
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.mtngroup.MtnGroupFragmentDirections

class MtnGroupAdapter(
    private val appViewModel: AppViewModel,
    private val mtnGroups: List<MtnGroup>
) :
    RecyclerView.Adapter<MtnGroupAdapter.MtnGroupHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MtnGroupHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemMtnGroupBinding.inflate(inflater, parent, false)

        return MtnGroupHolder(binding)
    }

    override fun onBindViewHolder(holder: MtnGroupHolder, position: Int) {
        holder.binding.mtnGroup = mtnGroups[position]

        val navDirection =
            MtnGroupFragmentDirections.openTripFragment()
        holder.binding.handler =
            OnClickHandler(
                mtnGroups[position],
                appViewModel,
                navDirection
            )
    }

    override fun getItemCount() = mtnGroups.size

    inner class MtnGroupHolder(val binding: ListItemMtnGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}