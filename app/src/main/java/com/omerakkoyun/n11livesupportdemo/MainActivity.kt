package com.omerakkoyun.n11livesupportdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.omerakkoyun.n11livesupportdemo.databinding.ActivityMainBinding
import com.omerakkoyun.n11livesupportdemo.presentation.chat.ChatActivity
import com.omerakkoyun.n11livesupportdemo.presentation.viewmodels.MainViewModel
import com.omerakkoyun.n11livesupportdemo.utils.helpers.JsonHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        viewModel.insertAllSteps(JsonHelper.loadStepListFromJsonRaw(applicationContext)) // room db, for send steps to server
    }

}