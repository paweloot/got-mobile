package com.paweloot.gotmobile.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paweloot.gotmobile.databinding.FragmentTripBinding

class TripFragment : Fragment() {

    private lateinit var binding: FragmentTripBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTripBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }
}
