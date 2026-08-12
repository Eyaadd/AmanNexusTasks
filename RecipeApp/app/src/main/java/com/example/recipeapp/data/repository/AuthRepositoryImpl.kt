package com.example.recipeapp.data.repository

import com.example.recipeapp.domain.repository.AuthRepository
import com.example.recipeapp.utils.safeCall
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signUp(
        email: String, password: String
    ): Result<Unit> {
        return safeCall {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }

    }

    override suspend fun login(
        email: String, password: String
    ): Result<Unit> {
        return safeCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}