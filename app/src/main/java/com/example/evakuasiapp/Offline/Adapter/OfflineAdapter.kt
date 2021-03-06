package com.example.evakuasiapp.Offline.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.evakuasiapp.Offline.Model.Offline
import com.example.evakuasiapp.databinding.RowOfflineBinding

class OfflineAdapter(val context : Context, val listGambar : ArrayList<Offline>) : RecyclerView.Adapter<OfflineAdapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view : RowOfflineBinding = RowOfflineBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val gbr = listGambar[position]
        Glide.with(context).load(gbr.photo).into(holder.binding.photo)
    }

    override fun getItemCount(): Int {
        return listGambar.size
    }

    inner class viewHolder(val binding : RowOfflineBinding) : RecyclerView.ViewHolder(binding.root)
}