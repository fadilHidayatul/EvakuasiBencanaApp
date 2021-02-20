package com.example.evakuasiapp.UserLocation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.FragmentUserLocationBinding

/**
 * A simple [Fragment] subclass.
 */
class UserLocationFragment : Fragment() {
    private lateinit var binding : FragmentUserLocationBinding

    fun UserLocationFragment() {
        // Required empty public constructor
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserLocationBinding.inflate(inflater,container,false)

        binding.loc.text = "LOKASI USER"


        return binding.root
    }


}
