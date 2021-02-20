package com.example.evakuasiapp.Intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.evakuasiapp.MainActivity
import com.example.evakuasiapp.R
import com.example.evakuasiapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var tr : Animation = AnimationUtils.loadAnimation(context, R.anim.transition)
        binding.Splash.startAnimation(tr)

        val intent = Intent(this, MainActivity::class.java)
        var timer : Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(4000)
                }catch (e: InterruptedException) {
                    e.printStackTrace()
                }finally {
                    startActivity(intent)
                    finish()
                }
            }
        }
        timer.start()

        }

    }
