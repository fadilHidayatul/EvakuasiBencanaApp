package com.example.evakuasiapp.Offline.Model

import com.example.evakuasiapp.R

object OfflineBandang {
    private var imageBandang = intArrayOf(
        R.drawable.bandang_1, R.drawable.bandang_2, R.drawable.bandang_3, R.drawable.bandang_4
    )

    val listData : ArrayList<Offline>
        get() {
            val list = arrayListOf<Offline>()
            for (position in imageBandang.indices){
                val offline = Offline()

                offline.photo = imageBandang[position]
                list.add(offline)
            }
            return list
        }
}