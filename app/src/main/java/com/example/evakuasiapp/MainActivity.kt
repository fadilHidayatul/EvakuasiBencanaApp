package com.example.evakuasiapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.evakuasiapp.Admin.LoginAdminActivity
import com.example.evakuasiapp.Banjir.BanjirFragment
import com.example.evakuasiapp.BanjirBandang.BanjirBandangFragment
import com.example.evakuasiapp.Gempa.GempaFragment
import com.example.evakuasiapp.Informasi.InformasiActivity
import com.example.evakuasiapp.Komentar.KomentarActivity
import com.example.evakuasiapp.Kontak.KontakActivity
import com.example.evakuasiapp.Longsor.LongsorFragment
import com.example.evakuasiapp.Offline.OfflineActivity
import com.example.evakuasiapp.Tsunami.TsunamiFragment
import com.example.evakuasiapp.UserLocation.UserLocationFragment
import com.example.evakuasiapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var context : Context

    private var doubleback = false
    private lateinit var toast : Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var menu : ImageView = findViewById(R.id.side_menu)
        menu.setOnClickListener(){
            binding.drawer.openDrawer(GravityCompat.START)
        }

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            R.string.drawer_open,
            R.string.drawer_close
        )
        binding.drawer.addDrawerListener(toggle)

        changeFragment(UserLocationFragment(), UserLocationFragment::class.java.simpleName)
        binding.navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_location_user -> changeFragment(
                UserLocationFragment(),
                UserLocationFragment::class.java.simpleName
            )
            R.id.evakuasi_gempa -> changeFragment(
                GempaFragment(),
                GempaFragment::class.java.simpleName
            )
            R.id.evakuasi_tsunami -> changeFragment(
                TsunamiFragment(),
                TsunamiFragment::class.java.simpleName
            )
            R.id.evakuasi_banjir -> changeFragment(
                BanjirFragment(),
                BanjirFragment::class.java.simpleName
            )
            R.id.evakuasi_banjirB -> changeFragment(
                BanjirBandangFragment(),
                BanjirBandangFragment::class.java.simpleName
            )
            R.id.evakuasi_longsor -> changeFragment(
                LongsorFragment(),
                LongsorFragment::class.java.simpleName
            )
            R.id.offline -> startActivity(Intent(this@MainActivity, OfflineActivity::class.java))
            R.id.admin -> startActivity(Intent(this@MainActivity, LoginAdminActivity::class.java))

            R.id.informasi_gempa -> {
                val intent = Intent(this,InformasiActivity::class.java)
                intent.putExtra("bencana","gempa")
                startActivity(intent)
            }
            R.id.informasi_tsunami -> {
                val intent = Intent(this,InformasiActivity::class.java)
                intent.putExtra("bencana","tsunami")
                startActivity(intent)
            }
            R.id.informasi_Cuaca -> {
                val intent = Intent(this,InformasiActivity::class.java)
                intent.putExtra("bencana", "cuaca")
                startActivity(intent)
            }

            R.id.komentar -> startActivity(Intent(this,KomentarActivity::class.java))
            R.id.contact -> startActivity(Intent(this,KontakActivity::class.java))
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun changeFragment(fragment: Fragment?, tag: String){
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        var current : Fragment? = fragmentManager.primaryNavigationFragment
        if (current != null){
            fragmentTransaction.hide(current)
        }

        var temp : Fragment? = fragmentManager.findFragmentByTag(tag)
        if (temp == null){
            temp = fragment
            fragmentTransaction.add(R.id.container, temp!!, tag)
        }else{
            fragmentTransaction.show(temp)
        }

        fragmentTransaction.setPrimaryNavigationFragment(temp)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitAllowingStateLoss()

    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else{
            if (doubleback){
                toast.cancel()
                super.onBackPressed()
                moveTaskToBack(true)
            }else{
                toast = Toast.makeText(this, "Tekan lagi untuk keluar",Toast.LENGTH_SHORT)
                toast.show()
                doubleback = true

                var handler = Handler()
                handler.postDelayed({ doubleback = false },2000)
            }
        }

    }


}

