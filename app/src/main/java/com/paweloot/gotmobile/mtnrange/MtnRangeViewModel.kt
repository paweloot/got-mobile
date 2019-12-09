package com.paweloot.gotmobile.mtnrange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MtnRangeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMtnRanges()
    }

    private fun getMtnRanges() {
        _response.value = "Tatry i Podtatrze"
    }
}