package com.paweloot.gotmobile.mtngroup

import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.model.repository.MtnGroupRepository

class MtnGroupViewModel : ViewModel() {

    private val mtnGroupRepository: MtnGroupRepository =
        MtnGroupRepository()

    val mtnGroups = mtnGroupRepository.mtnGroups

    fun fetchMtnGroups(mtnRange: MtnRange) {
        mtnGroupRepository.fetchMtnGroups(mtnRange)
    }
}