package com.example.com_us.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.com_us.databinding.ActivityLoginBinding
import com.example.com_us.databinding.ActivityQuestionCheckAnswerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}