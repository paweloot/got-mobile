package com.paweloot.gotmobile.mtngroup.view

import android.view.View
import androidx.navigation.NavDirections
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.model.entity.MtnGroup

class OnClickHandler(
    private val mtnGroup: MtnGroup,
    private val appViewModel: AppViewModel,
    private val navDirections: NavDirections
) {

    fun onMtnRangeClicked(view: View) {

        appViewModel.mtnGroup.value = mtnGroup
        appViewModel.newDestination.value = navDirections.actionId
    }
}