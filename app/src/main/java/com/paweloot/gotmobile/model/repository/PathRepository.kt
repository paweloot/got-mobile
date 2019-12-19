package com.paweloot.gotmobile.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.model.entity.Path

class PathRepository {

    private var _paths = MutableLiveData<List<Path>>()
    val paths: LiveData<List<Path>> = _paths

    init {
        getPaths()
    }

    private fun getPaths() {

        val paths = listOf(
            Path(1, 7, 2900, 116, 4, 3),
            Path(1, 5, 2100, 226, 4, 2),
            Path(2, 5, 2200, -40, 3, 3)
        )

        _paths.value = paths
    }
}