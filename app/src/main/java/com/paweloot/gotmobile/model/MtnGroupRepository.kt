package com.paweloot.gotmobile.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MtnGroupRepository {

    private val mtnGroupsLiveData = MutableLiveData<List<MtnGroup>>()

    fun getLiveData(): LiveData<List<MtnGroup>> {

        val mtnGroups = listOf(
            MtnGroup(1, "T.01", "Tatry Wysokie", 1),
            MtnGroup(2, "T.02", "Tatry Zachodnie", 1),
            MtnGroup(3, "T.03", "Podtatrze", 1)
        )

        mtnGroupsLiveData.value = mtnGroups

        return mtnGroupsLiveData
    }
}