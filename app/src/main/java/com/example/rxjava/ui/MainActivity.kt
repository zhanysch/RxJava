package com.example.rxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.rxjava.R
import com.example.rxjava.databinding.ActivityMainBinding
import com.example.rxjava.utils.doAfterTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel <MainViewModel>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // doAfterTextChanged()  этоextension функц из класса extension
        binding.etSearch.doAfterTextChanged{
            vm.search(it)
        }
        vm.data.observe(this, Observer {
            Toast.makeText(applicationContext,"searched", Toast.LENGTH_LONG).show()
        })
    }


}