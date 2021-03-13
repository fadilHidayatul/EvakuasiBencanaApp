package com.example.evakuasiapp.Offline.Model

import com.example.evakuasiapp.R

object OfflineTsunami {
    private var tsunamiImage = intArrayOf(
        R.drawable.tsunami4, R.drawable.tsunami5, R.drawable.tsunami1,R.drawable.tsunami2,R.drawable.tsunami3
    )

    val listTsunami : ArrayList<Offline>
        get() {
            val list = arrayListOf<Offline>()
            for (position in tsunamiImage.indices){
                val offline = Offline()

                offline.photo = tsunamiImage[position]
                list.add(offline)
            }
            return list
        }
}