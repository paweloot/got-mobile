package com.paweloot.gotmobile.mtngroup.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.databinding.FragmentMtnGroupBinding
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.mtngroup.MtnGroupViewModel


class MtnGroupFragment : Fragment(), MtnGroupAdapter.OnMtnGroupClickListener {

    private lateinit var viewModel: MtnGroupViewModel
    private lateinit var appViewModel: AppViewModel

    private lateinit var binding: FragmentMtnGroupBinding
    private lateinit var mtnGroupList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MtnGroupViewModel::class.java)
        appViewModel = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)

        binding = FragmentMtnGroupBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.mtnRange = appViewModel.mtnRange.value

        mtnGroupList = binding.mtnGroupList

        mtnGroupList.layoutManager = LinearLayoutManager(context)
        mtnGroupList.adapter = MtnGroupAdapter(this)

        observeMtnGroups()

        viewModel.fetchMtnGroups(appViewModel.mtnRange.value!!)

        return binding.root
    }

    override fun onMtnGroupClicked(mtnGroup: MtnGroup) {
        appViewModel.mtnGroup.value = mtnGroup
        appViewModel.newDestination.value =
            MtnGroupFragmentDirections.openTripFragment().actionId
    }

    private fun observeMtnGroups() {
        viewModel.mtnGroups.observe(viewLifecycleOwner, Observer { mtnGroups ->

            if (mtnGroups == null) {
                showConnectionErrorDialog()
            } else {
                binding.progressCircular.visibility = View.GONE
                binding.mtnGroupList.visibility = View.VISIBLE

                (mtnGroupList.adapter as MtnGroupAdapter).setMtnGroups(mtnGroups)
            }
        })
    }

    private fun showConnectionErrorDialog() {
        val alertDialog = AlertDialog.Builder(context)
            .setMessage(getString(R.string.alert_dialog_failed_to_connect))
            .setPositiveButton(
                R.string.button_continue
            ) { dialogInterface, i -> dialogInterface.dismiss() }

        alertDialog.show()
    }
}
