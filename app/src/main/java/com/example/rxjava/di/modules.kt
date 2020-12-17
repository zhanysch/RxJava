package com.example.rxjava.di

import com.example.rxjava.data.RetrofitBuilder
import com.example.rxjava.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module(){
    viewModel{MainViewModel(get())}

}

val repositoryModel = module(){
    single { RetrofitBuilder.buildRetrofit()  }
}

val appmodules = listOf(viewModelModule,repositoryModel)