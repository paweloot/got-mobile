package com.paweloot.gotmobile.mtngroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.model.repository.MtnGroupRepository

class MtnGroupViewModel : ViewModel() {

    private val mtnGroupRepository: MtnGroupRepository =
        MtnGroupRepository()

    fun fetchMtnGroups(mtnRange: MtnRange) {
        mtnGroupRepository.fetchMtnGroups(mtnRange)
    }

    fun getMtnGroups(): LiveData<List<MtnGroup>> {
        return mtnGroupRepository.mtnGroups
    }
}