package com.belalmhanafy.recordkeeper.Edit_Record

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.belalmhanafy.recordkeeper.R
import com.belalmhanafy.recordkeeper.cycling.INTENT_EXTRA_SCREEN_DATA
import com.belalmhanafy.recordkeeper.databinding.ActivityEditRecordBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class EditRecordActivity : AppCompatActivity() {

    private val screenData: ScreenData by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA, ScreenData::class.java) as ScreenData
        } else {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
        }
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
        binding.editRunningRecordDistanceTitle.text = "${screenData.record} $SHARED_PREFERENCES_RECORD_KEY"
        binding.textInputRecord.hint = screenData.recordFieldHint
        binding.saveButton.setOnClickListener {
            saveRecord()

        }

        binding.deleteRecordButton.setOnClickListener {
            deleteRecord()
        }
    }


    private fun displayRecord() {
        binding.editTextRecord.setText(
            recordPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCES_RECORD_KEY",
                null
            )
        )
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCES_DATE_KEY", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        val recordPreferences = getSharedPreferences(screenData.sharedPreferencesName, MODE_PRIVATE)

        recordPreferences.edit {
            putString("${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCES_RECORD_KEY", record)
            putString("${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCES_DATE_KEY", date)
        }
        Snackbar.make(binding.root,"The Record has been saved successfully , thank you :)", Snackbar.LENGTH_LONG).show()
        binding.root.postDelayed( {
            finish()
        },1000)
    }

    fun deleteRecord() {
        AlertDialog.Builder(this)
            .setTitle("Delete ${screenData.record} ${SHARED_PREFERENCES_RECORD_KEY}")
            .setMessage("Are you sure you want to delete this record ?")
            .setPositiveButton("Yes") { _, _ ->
                recordPreferences.edit {
                    remove("${screenData.record} $SHARED_PREFERENCES_RECORD_KEY")
                    remove("${screenData.record} $SHARED_PREFERENCES_DATE_KEY")
                }
                Snackbar.make(binding.root,"the record has been deleted successfully!", Snackbar.LENGTH_LONG).show()
                binding.root.postDelayed( {
                    finish()
                },1000)

            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .show() }

    data class ScreenData(

        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : java.io.Serializable

    companion object{
        const val SHARED_PREFERENCES_RECORD_KEY="record"
        const val SHARED_PREFERENCES_DATE_KEY="date"
    }

}