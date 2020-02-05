package com.paweloot.gotmobile.mtnrange.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.MainActivity
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.databinding.FragmentMtnRangeBinding
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.mtnrange.MtnRangeViewModel

class MtnRangeFragment : Fragment(), MtnRangeAdapter.OnMtnRangeClickedListener {

    private lateinit var viewModel: MtnRangeViewModel
    private lateinit var appViewModel: AppViewModel

    private lateinit var binding: FragmentMtnRangeBinding
    private lateinit var mtnRangeList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMtnRangeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        mtnRangeList = binding.mtnRangeList

        mtnRangeList.layoutManager = LinearLayoutManager(context)
        mtnRangeList.adapter = MtnRangeAdapter(this)

        viewModel = ViewModelProvider(this).get(MtnRangeViewModel::class.java)
        appViewModel = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)

        observeMtnRanges()
        handleBackPressing()

        return binding.root
    }

    override fun onMtnRangeClicked(mtnRange: MtnRange) {
        appViewModel.mtnRange.value = mtnRange
        appViewModel.newDestination.value =
            MtnRangeFragmentDirections.openMtnGroupFragment().actionId
    }

    private fun observeMtnRanges() {
        viewModel.mtnRanges.observe(viewLifecycleOwner, Observer { mtnRanges ->

            if (mtnRanges == null) {
                showConnectionErrorDialog()
            } else {
                binding.apply {
                    progressCircular.visibility = View.GONE
                    mtnRangeList.visibility = View.VISIBLE

                    (mtnRangeList.adapter as MtnRangeAdapter).setMtnRanges(mtnRanges)
                }
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

    private fun handleBackPressing() {
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val activity = requireActivity() as MainActivity
                    activity.showLogOutAlert()
                }
            })
    }
}
