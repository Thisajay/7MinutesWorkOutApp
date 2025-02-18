package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

       // val btnStart: FrameLayout = findViewById(R.id.btnStart)
        binding?.flStart?.setOnClickListener{
            val intent=Intent(this,ExerciseActivity::class.java)
            startActivity(intent)

        }
        binding?.flBmi?.setOnClickListener{
            val intent =Intent(this,BmiActivity::class.java)
            startActivity(intent)

        }
        binding?.flHistory?.setOnClickListener{
            val intent =Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}