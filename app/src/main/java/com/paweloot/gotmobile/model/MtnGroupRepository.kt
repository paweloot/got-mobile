package com.paweloot.gotmobile.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MtnGroupRepository {

    private val mtnGroupsLiveData = MutableLiveData<List<MtnGroup>>()

    fun getLiveData(): LiveData<List<MtnGroup>> {

        val mtnRanges = listOf(
            MtnRange(
                1,
                "Tatry i Podtatrze",
                "https://www.tatry-przewodnik.com.pl/images/jaworowe-turnie-1.jpg"
            ),
            MtnRange(
                2, "Beskidy Wschodnie",
                "https://www.smartage.pl/wp-content/uploads/2017/08/2743786.jpg"
            )
        )

        mtnGroupsLiveData.value = null

        return mtnGroupsLiveData
    }
}