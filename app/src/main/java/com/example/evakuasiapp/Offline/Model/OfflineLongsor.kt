package com.example.evakuasiapp.Offline.Model

import com.example.evakuasiapp.R

object OfflineLongsor {
    private var longsorImage = intArrayOf(
         R.drawable.longsor_1, R.drawable.longsor_2, R.drawable.longsor_3
    )

    val listData : ArrayList<Offline>
        get() {
            val list = arrayListOf<Offline>()
            for (position in longsorImage.indices){
                val offline = Offline()

                offline.photo = longsorImage[position]
                list.add(offline)
            }
            return list
        }

}