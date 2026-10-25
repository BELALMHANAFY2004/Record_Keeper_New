package com.belalmhanafy.recordkeeper.running

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.belalmhanafy.recordkeeper.R
import com.belalmhanafy.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditRunningRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val distance = intent.getStringExtra("Distance")
        binding.editRunningRecordDistanceTitle.text.toString()
        binding.editRunningRecordDistanceTitle.text = distance
        binding.saveButton.setOnClickListener { saveRecord(distance)
        finish()}
        displayRecord(distance)

    }

    private fun displayRecord(distance: String?) {

        val runningRecordActivity =getSharedPreferences("Running Records",MODE_PRIVATE)
        binding.editTextRecord.setText(runningRecordActivity.getString("$distance record",null))
        binding.editTextDate.setText(runningRecordActivity.getString("$distance date",null))
    }

    private fun saveRecord(distance: String?) {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        val runningShardPreferences = getSharedPreferences("Running Records",MODE_PRIVATE)

        runningShardPreferences.edit {
            putString("$distance record",record)
            putString("$distance date",date)
        }
        Toast.makeText(this, "The Record has been saves successfully , thank you :)", Toast.LENGTH_SHORT).show()
    }
}