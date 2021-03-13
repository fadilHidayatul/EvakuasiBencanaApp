package com.example.evakuasiapp.Offline.Model

import com.example.evakuasiapp.R

object OfflineGempa {
    private var gempaImage = intArrayOf(
        R.drawable.ic_gempa
    )

    val listDataGempa : ArrayList<Offline>
        get() {
            val list  = arrayListOf<Offline>()
            for (position in gempaImage.indices){
                val offline = Offline()     //model

                offline.photo = gempaImage[position]
                list.add(offline)
            }
            return list
        }
}