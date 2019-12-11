package com.paweloot.gotmobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.MtnRange

class AppViewModel : ViewModel() {

    private val newDestination = MutableLiveData<Int>()

    fun getNewDestination(): LiveData<Int> {
        return newDestination
    }

    fun setNewDestination(destinationId: Int) {
        newDestination.value = destinationId
    }

    val mtnRange = MutableLiveData<MtnRange>()
}