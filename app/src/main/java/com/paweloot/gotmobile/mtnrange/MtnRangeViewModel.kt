package com.paweloot.gotmobile.mtnrange

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.model.repository.MtnRangeRepository

class MtnRangeViewModel : ViewModel() {

    private val mtnRangeRepository: MtnRangeRepository =
        MtnRangeRepository()

    fun getMtnRanges(): LiveData<List<MtnRange>> {
        return mtnRangeRepository.mtnRanges
    }
}