package com.paweloot.gotmobile.mtnrange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.gotmobile.model.MtnRange
import com.paweloot.gotmobile.databinding.FragmentMtnRangeBinding


class MtnRangeFragment : Fragment() {

    private val viewModel: MtnRangeViewModel by lazy {
        ViewModelProviders.of(this).get(MtnRangeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMtnRangeBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.mtnRangeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MtnRangeAdapter(listOf(
                MtnRange(
                    1,
                    "Tatry i Podtatrze"
                )
            ))
        }

        viewModel.response.observe(this, Observer { response ->
            binding.mtnRangeList.adapter = MtnRangeAdapter(
                listOf(
                    MtnRange(
                        1,
                        "Tatry i Podtatrze"
                    ),
                    MtnRange(2, "Beskidy Wschodnie")
                )
            )
            binding.mtnRangeList.adapter?.notifyDataSetChanged()
        })


        return binding.root
    }

}
