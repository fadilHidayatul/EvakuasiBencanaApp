package com.example.evakuasiapp.Offline.Model

import com.example.evakuasiapp.R

object OfflineBanjir {
    private var banjirImage = intArrayOf(
        R.drawable.banjir_1, R.drawable.banjir_2, R.drawable.banjir_3, R.drawable.banjir_4
    )

    val listBanjir : ArrayList<Offline>
        get() {
            val list = arrayListOf<Offline>()

            for (position in banjirImage.indices){
                val offline = Offline()

                offline.photo = banjirImage[position]
                list.add(offline)

            }
            return list
        }
}