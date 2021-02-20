package com.example.evakuasiapp.UserLocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.EasyWayLocation.LOCATION_SETTING_REQUEST_CODE
import com.example.easywaylocation.GetLocationDetail
import com.example.easywaylocation.Listener
import com.example.easywaylocation.LocationData
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.databinding.FragmentUserLocationBinding

/**
 * A simple [Fragment] subclass.
 */
class UserLocationFragment : Fragment(), Listener, LocationData.AddressCallBack{
    private lateinit var binding : FragmentUserLocationBinding

    private var lat : Double = 0.0
    private var lgt : Double = 0.0

    private var easyLocation : EasyWayLocation? = null
    lateinit var getLocationDetail : GetLocationDetail

    var REQ_CODE : Int = 1

    fun UserLocationFragment() {
        // Required empty public constructor
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserLocationBinding.inflate(inflater, container, false)

        getLocationDetail = GetLocationDetail(this, requireContext())
        easyLocation = EasyWayLocation(requireContext(), false, this)

        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,REQ_CODE)
        if (permissionIsGranted()) {
            getLocationUser()
        } else {

        }

        return binding.root
    }

    fun checkPermission(permission : String, requestCode: Int){
        if (ContextCompat.checkSelfPermission(requireContext(),permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(requireActivity() , arrayOf(permission) ,requestCode )
        }else{
            Toast.makeText(requireContext(),
                "Permission already granted",
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQ_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                Toast.makeText(requireContext(),
                    "Camera Permission Granted",
                    Toast.LENGTH_SHORT)
                    .show();
            }else{
                Toast.makeText(requireContext(),
                    "Camera Permission Denied",
                    Toast.LENGTH_SHORT)
                    .show();
            }
        }
    }

    ////

    fun permissionIsGranted() : Boolean {
        var permissionState : Int = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun getLocationUser() {
        easyLocation!!.startLocation()
    }

    ////

    override fun locationData(locationData: LocationData?) {
        binding.location.text = locationData!!.full_address
    }

    override fun locationOn() {
        Toast.makeText(requireContext(), "Location On", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun currentLocation(location: Location?) {
        lat  = location!!.latitude
        lgt = location.longitude

        binding.latlong.text = lat.toString() + "" + lgt.toString()
        getLocationDetail.getAddress(location.latitude, location.longitude, "xxx")
    }

    override fun locationCancelled() {
        Toast.makeText(requireContext(), "Location Cancelled", Toast.LENGTH_SHORT).show()
    }

    ///

    override fun onResume() {
        super.onResume()
        easyLocation!!.startLocation()
    }

    override fun onPause() {
        super.onPause()
        easyLocation!!.endUpdates()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_SETTING_REQUEST_CODE){
            easyLocation!!.onActivityResult(resultCode)
        }
    }


}
