package com.example.mymovieapp.model.repository

import com.example.mymovieapp.data.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}