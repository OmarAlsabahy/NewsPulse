package com.example.newspulse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.newspulse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(GeneralFragment())

        binding.btnGeneral.setOnClickListener {
            replaceFragment(GeneralFragment())
        }


        binding.btnBusiness.setOnClickListener {
            replaceFragment(BusinessFragment())
        }

        binding.btnEntertainment.setOnClickListener {
            replaceFragment(EntertainmentFragment())
        }

        binding.btnHealth.setOnClickListener {
            replaceFragment(HealthFragment())
        }

        binding.btnScience.setOnClickListener {
            replaceFragment(ScienceFragment())
        }

        binding.btnSports.setOnClickListener {
            replaceFragment(SportsFragment())
        }

        binding.btnTechnology.setOnClickListener {
            replaceFragment(TechnologyFragment())
        }



    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentsContainer , fragment)
        fragmentTransaction.commit()
    }
}