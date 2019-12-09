package com.paweloot.gotmobile.mtnrange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.api.GotApi
import com.paweloot.gotmobile.api.MtnRange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MtnRangeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMtnRanges()
    }

    private fun getMtnRanges() {
        GotApi.retrofitService.getMtnRanges().enqueue(object : Callback<List<MtnRange>> {
            override fun onFailure(call: Call<List<MtnRange>>, t: Throwable) {
                _response.value = "Failure" + t.message
            }

            override fun onResponse(
                call: Call<List<MtnRange>>,
                response: Response<List<MtnRange>>
            ) {
                _response.value = "Success " + response.body()?.size
            }
        })

        _response.value = "Tatry i Podtatrze"
    }
}