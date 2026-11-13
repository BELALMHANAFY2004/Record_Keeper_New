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

const val INTENT_EXTRA_SCREEN_DATA = "Screen_Data"

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

        binding.containerLongestRide.setOnClickListener {
            launchRecordCyclingScreen(
                "Longest Ride",
                "Distance"
            )
        }
        binding.containerBiggestClimb.setOnClickListener {
            launchRecordCyclingScreen(
                "Biggest Climb",
                "Height"
            )
        }
        binding.textViewBestAverageSpeed.setOnClickListener {
            launchRecordCyclingScreen(
                "Best Average Speed",
                "Average Speed"
            )
        }
    }

    private fun displayRecord() {
        val cyclingSharedPreferences =
            requireContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text =
            cyclingSharedPreferences.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewLongestRideDate.text =
            cyclingSharedPreferences.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)

        binding.textViewBiggestClimbDate.text =
            cyclingSharedPreferences.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewBiggestClimbDate.text =
            cyclingSharedPreferences.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)

        binding.textViewHalfBestAverageSpeedValue.text =
            cyclingSharedPreferences.getString("Best Average Speed ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewBestAverageSpeedDate.text =
            cyclingSharedPreferences.getString("Best Average Speed ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
    }

    private fun launchRecordCyclingScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(
            INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(
                record,
                FILE_NAME, recordFieldHint
            )
        )
        startActivity(intent)
    }

    companion object {
        const val FILE_NAME = "cycling_shard_preferences"
    }
}