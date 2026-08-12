package com.example.recipeapp.di

import org.koin.dsl.module
import com.google.firebase.auth.FirebaseAuth


val firebaseModule = module {
    single<FirebaseAuth>{
        FirebaseAuth.getInstance()
    }
}