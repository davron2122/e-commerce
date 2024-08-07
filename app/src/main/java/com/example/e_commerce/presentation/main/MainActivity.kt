package com.example.e_commerce.presentation.main

import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.example.`e-commerce`.databinding.ActivityMainBinding
import dagger.hilt.android.lifecycle.HiltViewModel


class MainActivity : AppCompatActivity() {


    private val viewModel by viewModels<MainViewModel>()


    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        }


    }
