package com.paweloot.gotmobile.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.RetrofitApi
import com.paweloot.gotmobile.model.entity.MtnRange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MtnRangeRepository {

    private val _mtnRanges = MutableLiveData<List<MtnRange>>()
    val mtnRanges: LiveData<List<MtnRange>> = _mtnRanges

    init {
        fetchMtnRanges()
    }

    private fun fetchMtnRanges() {

        RetrofitApi.gotApi.getMtnRanges().enqueue(object : Callback<List<MtnRange>> {
            override fun onFailure(call: Call<List<MtnRange>>, t: Throwable) {
                Log.d("MtnRangeRepository", "onFailure: $t")
            }

            override fun onResponse(
                call: Call<List<MtnRange>>,
                response: Response<List<MtnRange>>
            ) {
                _mtnRanges.value = response.body()
            }
        })
    }
}