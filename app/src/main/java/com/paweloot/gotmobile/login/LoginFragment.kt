package com.paweloot.gotmobile.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paweloot.gotmobile.AppViewModel
import com.paweloot.gotmobile.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(AppViewModel::class.java)

        observeLoggedTourist()

        loginButton.setOnClickListener {

            emailInput.clearFocus()
            passwordInput.clearFocus()

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            authorizeTourist(email, password)
        }
    }

    private fun observeLoggedTourist() {
        viewModel.loggedTourist.observe(this, Observer { tourist ->
            if (tourist == null)
                onLoginFailure()
            else
                onLoginSuccessful()
        })
    }

    private fun authorizeTourist(email: String, password: String) {
        viewModel.authorizeTourist(email, password)
    }

    private fun onLoginFailure() {
        showFailureAlert()
    }

    private fun onLoginSuccessful() {
        //TODO
        viewModel.newDestination.value =
            LoginFragmentDirections.actionLoginFragmentToMtnRangeFragment().actionId
    }

    private fun showFailureAlert() {
        val dialog = AlertDialog.Builder(context)
            .setMessage("Błędne hasło, spróbuj ponownie!")
            .setPositiveButton(R.string.button_continue) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

        dialog.show()
    }
}