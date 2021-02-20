package com.example.evakuasiapp.Tsunami

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.FragmentTsunamiBinding

/**
 * A simple [Fragment] subclass.
 */
class TsunamiFragment : Fragment() {

    private lateinit var binding : FragmentTsunamiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTsunamiBinding.inflate(inflater,container,false)

        binding.loc.text = "ADA TSUNAMI!!!!"

        return binding.root
    }


}
