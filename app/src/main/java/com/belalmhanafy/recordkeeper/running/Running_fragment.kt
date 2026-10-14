package com.belalmhanafy.recordkeeper.running

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

        binding= RunningFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners()
    {
        binding.container5km.setOnClickListener { launchRecordRunningScreen("5Km")}
        binding.container10km.setOnClickListener { launchRecordRunningScreen("10KM") }
        binding.containerHalfMarathon.setOnClickListener { launchRecordRunningScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRecordRunningScreen("Marathon") }
    }

    private fun launchRecordRunningScreen(distance: String) {
       val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
    }