package com.paweloot.gotmobile.trip.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.paweloot.gotmobile.R

class MessageDialogFragment(private val success: Boolean) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.dialog_message, null)

            when (success) {
                true -> {
                    view.findViewById<TextView>(R.id.dialog_title)
                        .setText(R.string.dialog_success_title)
                    view.findViewById<TextView>(R.id.dialog_content)
                        .setText(R.string.dialog_success_content)
                }
                false -> {
                    view.findViewById<TextView>(R.id.dialog_title)
                        .setText(R.string.dialog_failure_title)
                    view.findViewById<TextView>(R.id.dialog_content)
                        .setText(R.string.dialog_failure_content)
                }
            }

            builder.setView(view)
                .setPositiveButton(R.string.button_continue) { dialog, id ->
                    dismiss()

                    if (success) {
                        findNavController()
                            .navigate(TripFragmentDirections.actionTripFragmentToMtnRangeFragment())
                    }
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}