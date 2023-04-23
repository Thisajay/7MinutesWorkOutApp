package com.example.a7minuteworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.HistoryRvItemBinding

class HistoryAdapter(private val items:ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding:HistoryRvItemBinding):RecyclerView.ViewHolder(binding.root){
        val historyItemRv=binding.historyRvItem
        val tvItem=binding.tvItem
        val tvPosition=binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HistoryRvItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val date:String=items.get(position)
        holder.tvPosition.text=(position+1).toString()
        holder.tvItem.text=date
        if (position %2 ==0){
            holder.historyItemRv.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorAccent))
        }else{
            holder.historyItemRv.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorPrimary))
        }
    }

    override fun getItemCount(): Int {
        return  items.size
    }
}