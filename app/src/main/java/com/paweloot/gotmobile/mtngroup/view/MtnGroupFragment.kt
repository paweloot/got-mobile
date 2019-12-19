package com.paweloot.gotmobile.mtngroup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.databinding.FragmentMtnGroupBinding
import com.paweloot.gotmobile.mtngroup.MtnGroupViewModel


class MtnGroupFragment : Fragment() {

    private val viewModel: MtnGroupViewModel by lazy {
        ViewModelProviders.of(this).get(MtnGroupViewModel::class.java)
    }

    private val appViewModel: AppViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(AppViewModel::class.java)
    }

    private lateinit var binding: FragmentMtnGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMtnGroupBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.mtnGroupList.layoutManager = LinearLayoutManager(context)

        binding.mtnRange = appViewModel.mtnRange.value

        observeMtnGroups()

        return binding.root
    }

    private fun observeMtnGroups() {
        viewModel.getMtnGroups().observe(this, Observer { mtnGroups ->

            binding.mtnGroupList.adapter =
                MtnGroupAdapter(
                    appViewModel,
                    mtnGroups
                )
        })
    }

}
