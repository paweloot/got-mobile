package com.paweloot.gotmobile.mtngroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.MtnGroup
import com.paweloot.gotmobile.model.MtnGroupRepository

class MtnGroupViewModel : ViewModel() {

    private val mtnGroupRepository: MtnGroupRepository = MtnGroupRepository()

    fun getMtnGroups(): LiveData<List<MtnGroup>> {
        return mtnGroupRepository.getLiveData()
    }
}