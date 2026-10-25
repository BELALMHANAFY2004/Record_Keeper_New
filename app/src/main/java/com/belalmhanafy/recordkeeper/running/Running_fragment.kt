package com.belalmhanafy.recordkeeper.running

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            requireContext().getSharedPreferences("Running Records", MODE_PRIVATE)
        binding.textView5kmValue.text = runningShardPreferences.getString("5Km record", null)
        binding.textView5kmDate.text = runningShardPreferences.getString("5Km date", null)

        binding.textView10kmValue.text = runningShardPreferences.getString("10KM record", null)
        binding.textView10kmDate.text = runningShardPreferences.getString("10KM date", null)

        binding.textViewHalfMarathonValue.text =
            runningShardPreferences.getString("Half Marathon record", null)
        binding.textViewHalfMarathonDate.text =
            runningShardPreferences.getString("Half Marathon date", null)

        binding.textViewMarathonValue.text =
            runningShardPreferences.getString("Marathon record", null)
        binding.textViewMarathonDate.text = runningShardPreferences.getString("Marathon date", null)
    }

    private fun launchRecordRunningScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
}