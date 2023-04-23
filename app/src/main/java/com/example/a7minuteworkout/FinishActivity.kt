package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    var binding:ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFinish?.setOnClickListener{
            finish()
        }
        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)

    }
    private fun addDateToDatabase(historyDao: HistoryDao){
        val c = Calendar.getInstance()
        val dateTime=c.time
        val sdf=SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity("date"))
            Log.e("Date:","Added..")
        }
    }
}