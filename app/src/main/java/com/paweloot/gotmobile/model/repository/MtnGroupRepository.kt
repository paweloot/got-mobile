package com.paweloot.gotmobile.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.RestApi
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.MtnRange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MtnGroupRepository"

class MtnGroupRepository {

    private val _mtnGroups = MutableLiveData<List<MtnGroup>>()
    val mtnGroups: LiveData<List<MtnGroup>> = _mtnGroups

    fun fetchMtnGroups(mtnRange: MtnRange) {

        RestApi.gotApi.getMtnGroups(mtnRange.id).enqueue(object : Callback<List<MtnGroup>> {
            override fun onFailure(call: Call<List<MtnGroup>>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed to fetch MtnGroups: $t")
                _mtnGroups.value = null
            }

            override fun onResponse(
                call: Call<List<MtnGroup>>,
                response: Response<List<MtnGroup>>
            ) {
                _mtnGroups.value = response.body()
            }
        })
    }
}