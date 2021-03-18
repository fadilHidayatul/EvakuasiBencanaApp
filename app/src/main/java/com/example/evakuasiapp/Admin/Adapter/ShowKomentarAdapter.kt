package com.example.evakuasiapp.Admin.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.evakuasiapp.Admin.Model.Comment
import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.RowShowKomentarBinding

class ShowKomentarAdapter(var context: Context, var list : List<Comment.DATABean>) : RecyclerView.Adapter<ShowKomentarAdapter.viewHolder>() {

    private lateinit var mContext : Context
    private lateinit var listKomentar: List<Comment.DATABean>

    init {
        this.mContext = context
        this.listKomentar = list
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowKomentarAdapter.viewHolder {
        val view : RowShowKomentarBinding = RowShowKomentarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowKomentarAdapter.viewHolder, position: Int) {
        holder.binding.jamKomentar.text = listKomentar[position].jam
        holder.binding.textKomentar.text = listKomentar[position].komentar

        var draw : Int = 0
        if (listKomentar[position].kategori == "1"){
            draw = R.drawable.ic_com_bandang
        }else if (listKomentar[position].kategori == "2"){
            draw = R.drawable.ic_com_banjir
        }else if (listKomentar[position].kategori == "3"){
            draw = R.drawable.ic_com_gempa
        }else if (listKomentar[position].kategori == "4"){
            draw = R.drawable.ic_com_longsor
        }else if (listKomentar[position].kategori == "5"){
            draw = R.drawable.ic_com_tsunami
        }
        Glide.with(mContext).load(draw).override(125,125).into(holder.binding.gambar)
    }

    override fun getItemCount(): Int {
        return listKomentar.size;
    }

    inner class viewHolder(val binding : RowShowKomentarBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}