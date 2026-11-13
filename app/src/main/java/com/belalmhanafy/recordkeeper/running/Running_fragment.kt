package com.belalmhanafy.recordkeeper.running

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.belalmhanafy.recordkeeper.Edit_Record.EditRecordActivity
import com.belalmhanafy.recordkeeper.cycling.INTENT_EXTRA_SCREEN_DATA
import com.belalmhanafy.recordkeeper.databinding.RunningFragmentBinding

class Running_fragment : Fragment() {

    private lateinit var binding: RunningFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = RunningFragmentBinding.inflate(layoutInflater, container, false)
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
        binding.container5km.setOnClickListener { launchRecordRunningScreen("5Km") }
        binding.container10km.setOnClickListener { launchRecordRunningScreen("10KM") }
        binding.containerHalfMarathon.setOnClickListener { launchRecordRunningScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRecordRunningScreen("Marathon") }
    }

    private fun displayRecord() {
        val runningShardPreferences =
            requireContext().getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        binding.textView5kmValue.text = runningShardPreferences.getString("5Km ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textView5kmDate.text = runningShardPreferences.getString("5Km ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)

        binding.textView10kmValue.text = runningShardPreferences.getString("10KM ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textView10kmDate.text = runningShardPreferences.getString("10KM ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)

        binding.textViewHalfMarathonValue.text =
            runningShardPreferences.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewHalfMarathonDate.text =
            runningShardPreferences.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)

        binding.textViewMarathonValue.text =
            runningShardPreferences.getString("Marathon ${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        binding.textViewMarathonDate.text = runningShardPreferences.getString("Marathon ${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
    }

    private fun launchRecordRunningScreen(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(
            INTENT_EXTRA_SCREEN_DATA,EditRecordActivity.ScreenData(distance,
                FILE_NAME, "Time")
        )
        startActivity(intent)
    }
    companion object{
        const val FILE_NAME="running_shard_preferences"
    }

    }