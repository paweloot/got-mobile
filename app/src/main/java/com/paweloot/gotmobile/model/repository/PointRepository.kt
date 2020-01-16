package com.paweloot.gotmobile.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.RetrofitApi
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.Point
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PointRepository"

/**
 * Repozytorium przechowujące oraz aktualizujące listę punktów pobraną z bazy danych,
 * która może być obserwowana przez ViewModel.
 */
class PointRepository {

    private val _points = MutableLiveData<List<Point>>()
    /**
     * Lista pobranych punktów.
     */
    val points: LiveData<List<Point>> = _points

    /**
     * Pobiera punkty z bazy danych dla wybranej grupy górskiej.
     * Jeśli operacja zakończy się powodzeniem, lista przechowywana w klasie jest
     * aktualizowana. W przeciwnym wypadku logowany jest błąd o niepowodzeniu.
     *
     * @param mtnGroup wybrana grupa górska
     */
    fun fetchPoints(mtnGroup: MtnGroup): List<Point>? {

        var points: List<Point>? = null

        RetrofitApi.gotApi.getPoints(mtnGroup.id).enqueue(object : Callback<List<Point>> {
            override fun onFailure(call: Call<List<Point>>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed to fetch Points: $t")
            }

            override fun onResponse(call: Call<List<Point>>, response: Response<List<Point>>) {
                _points.value = response.body()
                points = response.body()
            }
        })
        
        return points
    }

    /**
     * Pobiera punkty z bazy danych, które są osiągalne z wybranego punktu.
     * W przypadku powodzenia operacji, lista przechowywana w klasie jest aktualizowana.
     * W p.p. logowany jest błąd o niepowodzeniu.
     */
    fun filterPoints(pointFrom: Point) {

        RetrofitApi.gotApi.getFilteredPoints(pointFrom.id).enqueue(object : Callback<List<Point>> {
            override fun onFailure(call: Call<List<Point>>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed to fetch filtered Points: $t")
            }

            override fun onResponse(call: Call<List<Point>>, response: Response<List<Point>>) {
                _points.value = response.body()
            }
        })
    }
}