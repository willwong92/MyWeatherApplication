package com.example.myweatherapplication.presentation.ui.activity

import android.view.LayoutInflater
import com.example.myweatherapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)
}