package com.belalmhanafy.recordkeeper

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.belalmhanafy.recordkeeper.cycling.cycling_fragment
import com.belalmhanafy.recordkeeper.databinding.ActivityMainBinding
import com.belalmhanafy.recordkeeper.running.Running_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNav.setOnNavigationItemSelectedListener(this)
        onBackPressedDispatcher.addCallback {
            showDialog()
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.running_nav -> {
                supportFragmentManager.commit {
                    replace(R.id.FrameContent, Running_fragment())
                }
                return true

            }

            R.id.cycling_nav -> {
                supportFragmentManager.commit {
                    replace(R.id.FrameContent, cycling_fragment())
                }
                return true

            }

            else -> {
                return false
            }
        }

    }

    fun showDialog() {
        AlertDialog.Builder(this).setTitle("Warning!")
            .setPositiveButton("Yes") { firstParameter, secondParameter -> finish()}
            .setView(R.layout.dialog_warning)
            .setNegativeButton("No") { dialog, which -> dialog.dismiss()}
            .setNeutralButton("More Info"){dialog,_ -> Toast.makeText(this,"this is where you can find out more about the app",
                Toast.LENGTH_LONG).show()
            dialog.dismiss() }.show()

    }

}
