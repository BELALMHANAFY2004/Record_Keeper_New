package com.belalmhanafy.recordkeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.belalmhanafy.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class Edit_Cycling_Record_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ride_type =intent.getStringExtra("Ride")
        binding.editCyclingRecordTypeTitle.text=ride_type
    }
}