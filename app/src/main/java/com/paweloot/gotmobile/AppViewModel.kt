package com.paweloot.gotmobile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.model.repository.UserRepository

private const val TAG = "AppViewModel"

class AppViewModel : ViewModel() {

    private val userRepository = UserRepository()

    val loggedTourist = userRepository.loggedTourist

    val newDestination = MutableLiveData<Int>()

    val mtnRange = MutableLiveData<MtnRange>()

    val mtnGroup = MutableLiveData<MtnGroup>()

    fun authorizeTourist(email: String, password: String) {
        userRepository.authorizeTourist(email, password)
    }
}