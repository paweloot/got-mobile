package com.paweloot.gotmobile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.api.RetrofitApi
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.model.entity.Tourist
import com.paweloot.gotmobile.utility.Hasher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

private const val TAG = "AppViewModel"

class AppViewModel : ViewModel() {

    var loggedTourist: Tourist? = null

    val newDestination = MutableLiveData<Int>()

    val mtnRange = MutableLiveData<MtnRange>()

    val mtnGroup = MutableLiveData<MtnGroup>()

    init {
        val email = "paweloot@gmail.com"
        val bytes = "paweloot".toByteArray(Charset.forName("UTF-8"))
//        val encodedPassword = MessageDigest.getInstance("SHA-256")
//            .digest(bytes)
//            .toString()
        val encodedPassword = Hasher().hash("paweloot")

        RetrofitApi.gotApi.authorizeTourist(email, encodedPassword)
            .enqueue(object : Callback<Tourist> {
                override fun onFailure(call: Call<Tourist>, t: Throwable) {
                    Log.d(TAG, "onFailure: Failed to authorize a tourist $email: $t")
                }

                override fun onResponse(call: Call<Tourist>, response: Response<Tourist>) {
                    loggedTourist = response.body()
                }
            })
    }
}