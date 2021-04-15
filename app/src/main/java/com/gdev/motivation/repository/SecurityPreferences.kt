package com.gdev.motivation.repository

import android.content.Context
import com.gdev.motivation.util.Constants

class SecurityPreferences(context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    /*
    *Shared Name
    **/
    fun savePersonName(name: String){
        saveString(Constants.KEY.PERSON_NAME,name)
    }

    fun getPersonName(): String {
        return getString(Constants.KEY.PERSON_NAME)
    }

    /*
    *Shared Strings
    **/
    private fun getString(key: String): String{
        return mSharedPreferences.getString(key,"").toString()
    }

    private fun saveString(key: String, value:String){
        mSharedPreferences.edit().putString(key, value).apply();
    }
}