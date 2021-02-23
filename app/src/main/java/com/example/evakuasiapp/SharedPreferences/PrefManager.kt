package com.example.evakuasiapp.SharedPreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    var SP : SharedPreferences? = null
     var editor: SharedPreferences.Editor? = null
     var mcontext: Context? = null

    var PRIVATE_MODE : Int = 0

    val PREF_MANAGER : String = "EVAKUASI"
    val SESSION_KEY : String = "KEY"
    val USERNAME : String = "USERNAME"
    val TOKEN : String = "TOKEN"

    //initialize Shared Preferences
    init {
        mcontext = context
        SP = mcontext!!.getSharedPreferences(PREF_MANAGER, PRIVATE_MODE)
        editor = SP?.edit()
    }

    //Session User
    fun saveSession(){
        editor!!.putBoolean(SESSION_KEY, true)
        editor!!.commit()
    }

    fun getSession() : Boolean {
        return SP!!.getBoolean(SESSION_KEY, false)
    }

    fun removeSession() {
        editor!!.putBoolean(SESSION_KEY, false)
        editor!!.commit()
    }

    //user
    fun setUsername(key: String, value: String){
        editor!!.putString(key, value)
        editor!!.commit()
    }
    fun getUsername() : String? {
        return SP!!.getString(USERNAME, "")
    }

    fun setToken(key: String, value: String){
        editor!!.putString(key, value)
        editor!!.commit()
    }
    fun getToken() : String? {
        return SP!!.getString(TOKEN, "")
    }



}