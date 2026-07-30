package com.example.nasaapp.di

import com.example.nasaapp.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel(qualifier = homeViewModelNamed){
        HomeViewModel(
            repository = get(qualifier = asteroidRepositoryImpNamed)
        )
    }
}