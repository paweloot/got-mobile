package com.paweloot.gotmobile.mtnrange.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import com.paweloot.gotmobile.databinding.FragmentMtnRangeBinding
import com.paweloot.gotmobile.mtnrange.MtnRangeViewModel


class MtnRangeFragment : Fragment() {

    private val viewModel: MtnRangeViewModel by lazy {
        ViewModelProviders.of(this).get(MtnRangeViewModel::class.java)
    }

    private lateinit var appViewModel: AppViewModel

    private lateinit var binding: FragmentMtnRangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMtnRangeBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.mtnRangeList.layoutManager = LinearLayoutManager(context)

        appViewModel = ViewModelProviders.of(requireActivity()).get(AppViewModel::class.java)

        observeMtnRanges()

        return binding.root
    }

    private fun observeMtnRanges() {
        viewModel.mtnRanges.observe(this, Observer { mtnRanges ->

            if (mtnRanges == null) {
                val alertDialog = AlertDialog.Builder(context)
                    .setMessage(getString(R.string.alert_dialog_failed_to_connect))
                    .setPositiveButton(
                        R.string.button_continue
                    ) { dialogInterface, i -> dialogInterface.dismiss() }

                alertDialog.show()
            } else {
                binding.progressCircular.visibility = View.GONE
                binding.mtnRangeList.visibility = View.VISIBLE

                binding.mtnRangeList.adapter =
                    MtnRangeAdapter(
                        appViewModel,
                        mtnRanges
                    )
            }
        })
    }

}
