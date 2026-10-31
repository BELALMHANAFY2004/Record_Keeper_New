package com.belalmhanafy.recordkeeper.Edit_Record

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.belalmhanafy.recordkeeper.R
import com.belalmhanafy.recordkeeper.databinding.ActivityEditRecordBinding
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class EditRecordActivity : AppCompatActivity() {

    private val screenData: ScreenData by lazy {
        intent.getSerializableExtra("Screen_Data") as ScreenData
    }

    private val recordPreferences: SharedPreferences by lazy {
        getSharedPreferences(screenData.sharedPreferencesName, MODE_PRIVATE)
    }

    private lateinit var binding: ActivityEditRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupUI()
        displayRecord()
    }

    private fun setupUI() {
        binding.editRunningRecordDistanceTitle.text = "${screenData.record} Record"
        binding.textInputRecord.hint = screenData.recordFieldHint
        binding.saveButton.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.deleteRecordButton.setOnClickListener {
            deleteRecord()
            finish()
        }
    }


    private fun displayRecord() {
        binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} record", null))
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        val recordPreferences = getSharedPreferences(screenData.sharedPreferencesName, MODE_PRIVATE)

        recordPreferences.edit {
            putString("${this@EditRecordActivity.screenData.record} record", record)
            putString("${this@EditRecordActivity.screenData.record} date", date)
        }
        Toast.makeText(
            this,
            "The Record has been saved successfully , thank you :)",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun deleteRecord() {
        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
        Toast.makeText(this, "the record has been deleted successfully", Toast.LENGTH_SHORT).show()
    }

    data class ScreenData(

        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : java.io.Serializable


}