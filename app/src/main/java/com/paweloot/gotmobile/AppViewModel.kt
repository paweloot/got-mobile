package com.paweloot.gotmobile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.MtnRange

class AppViewModel : ViewModel() {

    val newDestination = MutableLiveData<Int>()

    val mtnRange = MutableLiveData<MtnRange>()
}