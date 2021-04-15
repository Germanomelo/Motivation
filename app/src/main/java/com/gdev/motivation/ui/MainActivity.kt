package com.gdev.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gdev.motivation.R
import com.gdev.motivation.databinding.ActivityMainBinding
import com.gdev.motivation.repository.Mock
import com.gdev.motivation.repository.SecurityPreferences
import com.gdev.motivation.util.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSharedPreferences: SecurityPreferences
    private var phraseFilter = Constants.PHRASE_FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        mSharedPreferences = SecurityPreferences(this)
        binding.textName.text ="Olá, ${mSharedPreferences.getPersonName()}"

        handleEvent();

        loadInit()
    }

    private fun loadInit(){
        binding.imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()
    }

    private fun handleEvent() {
        binding.imageAll.setOnClickListener(View.OnClickListener {
            phraseFilter =Constants.PHRASE_FILTER.ALL
            handleTintButton(phraseFilter)
        })

        binding.imageHappy.setOnClickListener(View.OnClickListener {
            phraseFilter =Constants.PHRASE_FILTER.HAPPY
            handleTintButton(phraseFilter)
        })

        binding.imageMorning.setOnClickListener(View.OnClickListener {
            phraseFilter =Constants.PHRASE_FILTER.MORNING
            handleTintButton(phraseFilter)
        })

        binding.buttonNewFrase.setOnClickListener(View.OnClickListener {
            handleNewPhrase()
        })

        binding.textName.setOnClickListener(View.OnClickListener {
            mSharedPreferences.savePersonName("")
            startActivity(Intent(this,SplashActivity::class.java))
            finish()
        })
    }

    private fun handleNewPhrase(){
        binding.textPhrase.text = Mock().getPhrase(phraseFilter);
    }

    private fun handleTintButton(id: Int){
        binding.imageAll.setColorFilter(resources.getColor(R.color.white))
        binding.imageHappy.setColorFilter(resources.getColor(R.color.white))
        binding.imageMorning.setColorFilter(resources.getColor(R.color.white))

        when(id){
            Constants.PHRASE_FILTER.ALL->binding.imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
            Constants.PHRASE_FILTER.HAPPY->binding.imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
            Constants.PHRASE_FILTER.MORNING->binding.imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
        }
    }
}