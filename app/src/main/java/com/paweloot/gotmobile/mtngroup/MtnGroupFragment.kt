package com.paweloot.gotmobile.mtngroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paweloot.gotmobile.databinding.FragmentMtnGroupBinding


class MtnGroupFragment : Fragment() {

    private val viewModel: MtnGroupViewModel by lazy {
        ViewModelProviders.of(this).get(MtnGroupViewModel::class.java)
    }

    private lateinit var binding: FragmentMtnGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMtnGroupBinding.inflate(inflater)


        observeMtnRanges()

        return binding.root
    }

    private fun observeMtnRanges() {
        viewModel.getMtnGroups().observe(this, Observer { mtnGroups ->

        })
    }

}
