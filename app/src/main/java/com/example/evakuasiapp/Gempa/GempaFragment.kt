package com.example.evakuasiapp.Gempa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.evakuasiapp.JalurEvakuasi.JalurEvakuasiActivity

import com.example.evakuasiapp.R
import com.example.evakuasiapp.SharedPreferences.PrefManager
import com.example.evakuasiapp.databinding.FragmentGempaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_gempa.*
import kotlinx.android.synthetic.main.fragment_gempa.view.*

/**
 * A simple [Fragment] subclass.
 */
class GempaFragment : Fragment() {
    private lateinit var binding : FragmentGempaBinding

    private lateinit var gmaps : GoogleMap
    private lateinit var cameraPosition: CameraPosition
    private lateinit var center : LatLng
    private var markerOptions = MarkerOptions()

    private lateinit var manager : PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGempaBinding.inflate(inflater,container,false)
        manager = PrefManager(context!!)

        var toolbar : TextView = activity!!.findViewById(R.id.barInformasi)
        toolbar.text = "Titik Rawan Gempa"

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        showMap()

//        var lt : Double = manager.getLat()!!.toDouble()
//        var lg : Double = manager.getLong()!!.toDouble()
//        Toast.makeText(requireContext(), "$lt , $lg", Toast.LENGTH_SHORT).show()

        binding.btnEvakuasiGempa.setOnClickListener {
            var intent = Intent(context,JalurEvakuasiActivity::class.java)
            intent.putExtra("kategori","3")
            startActivity(intent)
        }

        return binding.root
    }

    private fun showMap() {
        binding.mapView.getMapAsync(object : OnMapReadyCallback{
            override fun onMapReady(p0: GoogleMap?) {
                gmaps = p0!!
                center = LatLng(-0.9447304,100.3733914)
                cameraPosition = CameraPosition.Builder().target(center).zoom(12F).build()
                p0.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


            }

        })
    }


}
