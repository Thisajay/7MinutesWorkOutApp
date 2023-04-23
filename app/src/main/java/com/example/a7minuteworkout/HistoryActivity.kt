package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding

import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.historyToolbar)

        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="HISTORY "
        }
        binding?.historyToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
        val dao = (application as WorkOutApp).db.historyDao()
        getAllCompletedDates(dao)

    }
    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{ allCompletedDatesList ->

            /* if (allCompletedDatesList==null){
                 binding?.tvTitle?.visibility=View.VISIBLE
                 binding?.historyRv?.visibility=View.VISIBLE
                 binding?.tvNoData?.visibility=View.INVISIBLE

                 binding?.historyRv?.layoutManager=LinearLayoutManager(this@HistoryActivity)
                 val dates=ArrayList<String>()

                 for (date in allCompletedDatesList.date){
                     dates.add(date.toString())
                 }
                 val historyAdapter=HistoryAdapter(dates)
                 binding?.historyRv?.adapter=historyAdapter
             }else

             {
                 binding?.tvTitle?.visibility=View.GONE
                 binding?.historyRv?.visibility=View.GONE
                 binding?.tvNoData?.visibility=View.VISIBLE
           */  }/*
//

            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}