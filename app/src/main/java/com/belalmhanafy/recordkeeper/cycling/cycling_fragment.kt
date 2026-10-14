package com.belalmhanafy.recordkeeper.cycling

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.belalmhanafy.recordkeeper.databinding.FragmentCyclingFragmentBinding

class cycling_fragment : Fragment() {

    private lateinit var binding: FragmentCyclingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        binding.containerLongestRide.setOnClickListener { launchRecordCyclingScreen("Longest Ride") }
        binding.containerBiggestClimb.setOnClickListener { launchRecordCyclingScreen("Biggest Climb") }
        binding.textViewBestAverageSpeed.setOnClickListener { launchRecordCyclingScreen("Best Average Speed") }
    }

    private fun launchRecordCyclingScreen(ride: String) {
        val intent = Intent(context, Edit_Cycling_Record_Activity::class.java)
        intent.putExtra("Ride",ride)
        startActivity(intent)
    }
}