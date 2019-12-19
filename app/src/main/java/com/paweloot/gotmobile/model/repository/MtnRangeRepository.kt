package com.paweloot.gotmobile.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.model.entity.MtnRange

class MtnRangeRepository {

    private val mtnRangesLiveData = MutableLiveData<List<MtnRange>>()

    fun getLiveData(): LiveData<List<MtnRange>> {

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

        mtnRangesLiveData.value = mtnRanges

        return mtnRangesLiveData
    }
}