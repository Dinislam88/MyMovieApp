package com.example.mymovieapp.view.viewmodel

import com.example.mymovieapp.data.User
import com.example.mymovieapp.model.repository.FirebaseRepository
import com.example.mymovieapp.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {

    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}