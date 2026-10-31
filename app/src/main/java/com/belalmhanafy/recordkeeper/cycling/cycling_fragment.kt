package com.belalmhanafy.recordkeeper.cycling

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.belalmhanafy.recordkeeper.Edit_Record.EditRecordActivity
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

    override fun onResume() {
        super.onResume()
        displayRecord()
    }
    private fun setupClickListeners() {

        binding.containerLongestRide.setOnClickListener { launchRecordCyclingScreen("Longest Ride","Distance") }
        binding.containerBiggestClimb.setOnClickListener { launchRecordCyclingScreen("Biggest Climb" , "Height") }
        binding.textViewBestAverageSpeed.setOnClickListener { launchRecordCyclingScreen("Best Average Speed" , "Average Speed") }
    }

    private fun displayRecord() {
        val cyclingSharedPreferences = requireContext().getSharedPreferences("Cycling Records", Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text= cyclingSharedPreferences.getString("Longest Ride record",null)
        binding.textViewLongestRideDate.text=cyclingSharedPreferences.getString("Longest Ride date",null)

        binding.textViewBiggestClimbDate.text=cyclingSharedPreferences.getString("Biggest Climb record",null)
        binding.textViewBiggestClimbDate.text=cyclingSharedPreferences.getString("Biggest Climb date",null)

        binding.textViewHalfBestAverageSpeedValue.text=cyclingSharedPreferences.getString("Best Average Speed record",null)
        binding.textViewBestAverageSpeedDate.text=cyclingSharedPreferences.getString("Best Average Speed date",null)
    }

    private fun launchRecordCyclingScreen(record: String , recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("Screen_Data", EditRecordActivity.ScreenData(record,"Cycling Records", recordFieldHint))
        startActivity(intent)
    }
}