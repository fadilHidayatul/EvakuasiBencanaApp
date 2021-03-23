package com.example.evakuasiapp.JalurEvakuasi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.evakuasiapp.JalurEvakuasi.JalurEvakuasi
import com.example.evakuasiapp.JalurEvakuasi.MapEvakuasiActivity
import com.example.evakuasiapp.databinding.RowJalurBinding
import java.util.*

class JalurEvakuasiAdapter(var context: Context, var list: List<JalurEvakuasi.DATABean>)
    : RecyclerView.Adapter<JalurEvakuasiAdapter.viewHolder>() , Filterable {

    private var mContext: Context
    private var mList: List<JalurEvakuasi.DATABean>
    private var listFilter : List<JalurEvakuasi.DATABean>

    init {
        this.mContext = context
        this.mList = list
        this.listFilter = list
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        val view : RowJalurBinding = RowJalurBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: JalurEvakuasiAdapter.viewHolder, position: Int) {
        var j = listFilter[position].jarak.toDouble()

        holder.binding.placeName.text = listFilter[position].tempat
        holder.binding.distance.text =  String.format("%.2f",j/1000) + " km"
        holder.binding.rowPlace.setOnClickListener(){
            var intent = Intent(context,MapEvakuasiActivity::class.java)
            intent.putExtra("tempat", listFilter[position].tempat)
            intent.putExtra("alamat",listFilter[position].alamat)
            intent.putExtra("kecamatan",listFilter[position].kecamatan)
            intent.putExtra("lat",listFilter[position].lat)
            intent.putExtra("long",listFilter[position].longX)
            intent.putExtra("daya",listFilter[position].daya)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listFilter.size
    }

    inner class viewHolder(val binding: RowJalurBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSeq : String = p0.toString()
                if (charSeq.isEmpty()){
                    listFilter = list
                }else{
                    val filtered: MutableList<JalurEvakuasi.DATABean> = ArrayList<JalurEvakuasi.DATABean>()
                    for (name in list) {
                        if (name.tempat.toLowerCase().contains(charSeq.toLowerCase())) {
                            filtered.add(name)
                        }
                        listFilter = filtered
                    }
                }

                var results = FilterResults()
                results.values = listFilter

                return results

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listFilter = p1?.values as List<JalurEvakuasi.DATABean>
                notifyDataSetChanged()
            }

        }
    }

}

