package com.paweloot.gotmobile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.MtnRange

class AppViewModel : ViewModel() {

    val newDestination = MutableLiveData<Int>()

    val mtnRange = MutableLiveData<MtnRange>()

    val mtnGroup = MutableLiveData<MtnGroup>()
}