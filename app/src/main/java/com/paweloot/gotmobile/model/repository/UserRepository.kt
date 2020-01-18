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

    fun authorizeTourist(email: String, password: String) {

        val encodedPassword = Hasher().hash(password)

        RetrofitApi.gotApi.authorizeTourist(email, encodedPassword)
            .enqueue(object : Callback<Tourist> {
                override fun onFailure(call: Call<Tourist>, t: Throwable) {
                    Log.d(TAG, "onFailure: Failed to authorize a tourist $email: $t")
                    _loggedTourist.value = null
                }

                override fun onResponse(call: Call<Tourist>, response: Response<Tourist>) {
                    _loggedTourist.value = response.body()
                }
            })
    }

    fun logOut() {
        _loggedTourist.value = null
    }
}