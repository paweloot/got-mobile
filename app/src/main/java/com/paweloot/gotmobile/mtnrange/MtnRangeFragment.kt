package com.paweloot.gotmobile.mtnrange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.databinding.FragmentMtnRangeBinding


class MtnRangeFragment : Fragment() {

    private val viewModel: MtnRangeViewModel by lazy {
        ViewModelProviders.of(this).get(MtnRangeViewModel::class.java)
    }

    private lateinit var binding: FragmentMtnRangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMtnRangeBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.mtnRangeList.layoutManager = LinearLayoutManager(context)

        observeMtnRanges()

        return binding.root
    }

    private fun observeMtnRanges() {
        viewModel.getMtnRanges().observe(this, Observer { mtnRanges ->

            binding.mtnRangeList.adapter = MtnRangeAdapter(requireContext(), mtnRanges)
        })
    }

}
