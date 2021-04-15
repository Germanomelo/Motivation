package com.gdev.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gdev.motivation.databinding.ActivitySplashBinding
import com.gdev.motivation.repository.SecurityPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding
    private lateinit var mSharedPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportActionBar != null){
            supportActionBar!!.hide();
        }

        mSharedPreferences = SecurityPreferences(this)

        verifyName()
    }

    private fun verifyName(){
        val name = mSharedPreferences.getPersonName()
        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun onClickSave(view: View) {
        if(binding.editName.text.toString() != ""){
            mSharedPreferences.savePersonName(binding.editName.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Informe seu nome!",Toast.LENGTH_SHORT).show()
        }
    }
}