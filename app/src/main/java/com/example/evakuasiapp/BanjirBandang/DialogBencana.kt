package com.example.evakuasiapp.BanjirBandang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.databinding.BencanaDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass.

 */
class DialogBencana : BottomSheetDialogFragment() {
    private lateinit var binding : BencanaDialogBinding
    private lateinit var manager : PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BencanaDialogBinding.inflate(inflater,container,false)
        manager = PrefManager(context!!)

        binding.txtAlamat.text = manager.getAlamatBencana()
        binding.txtKecamatan.text = manager.getKecamatanBencana()
        binding.txtRadius.text = "100 m"

        return binding.root
    }


}
