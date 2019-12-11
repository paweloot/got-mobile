package com.paweloot.gotmobile.mtnrange

import android.view.View
import androidx.navigation.NavDirections
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.model.MtnRange

class OnClickHandler(
    private val mtnRange: MtnRange,
    private val appViewModel: AppViewModel,
    private val navDirections: NavDirections
) {

    fun onMtnRangeClicked(view: View) {

        appViewModel.mtnRange.value = mtnRange
        appViewModel.newDestination.value = navDirections.actionId
    }
}