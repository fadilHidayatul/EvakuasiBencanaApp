package com.example.evakuasiapp.Gempa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.FragmentGempaBinding

/**
 * A simple [Fragment] subclass.
 */
class GempaFragment : Fragment() {
    private lateinit var binding : FragmentGempaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentGempaBinding.inflate(inflater,container,false)

        binding.loc.text = "INFO GEMPA!!!!"
        return binding.root
    }


}
