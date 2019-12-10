package com.paweloot.gotmobile.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.GotApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MtnRangeRepository {

    private lateinit var mtnRangesLiveData: LiveData<List<MtnRange>>

    fun getLiveData(): LiveData<List<MtnRange>> {

        val call: Call<List<MtnRange>> = GotApi.retrofitService.getMtnRanges()

        call.enqueue(object : Callback<List<MtnRange>> {
            override fun onResponse(
                call: Call<List<MtnRange>>,
                response: Response<List<MtnRange>>
            ) {

                val mtnRanges = response.body()
                if (mtnRanges != null) {
                    mtnRangesLiveData = MutableLiveData(mtnRanges)
                }
            }

            override fun onFailure(call: Call<List<MtnRange>>, t: Throwable) {}
        })

        return mtnRangesLiveData
    }
}