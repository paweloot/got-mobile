package com.paweloot.gotmobile.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.RetrofitApi
import com.paweloot.gotmobile.model.entity.Tourist
import com.paweloot.gotmobile.utility.Hasher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserRepository"

class UserRepository {

    private val _loggedTourist = MutableLiveData<Tourist>()
    val loggedTourist: LiveData<Tourist> = _loggedTourist

    init {
        authorizeTourist()
    }

    private fun authorizeTourist() {
        val email = "paweloot@gmail.com"
        val encodedPassword = Hasher().hash("paweloot")

        RetrofitApi.gotApi.authorizeTourist(email, encodedPassword)
            .enqueue(object : Callback<Tourist> {
                override fun onFailure(call: Call<Tourist>, t: Throwable) {
                    Log.d(TAG, "onFailure: Failed to authorize a tourist $email: $t")
                }

                override fun onResponse(call: Call<Tourist>, response: Response<Tourist>) {
                    _loggedTourist.value = response.body()
                }
            })
    }
}