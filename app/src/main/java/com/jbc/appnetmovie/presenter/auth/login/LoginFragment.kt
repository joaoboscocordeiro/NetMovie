package com.jbc.appnetmovie.presenter.auth.login

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentLoginBinding
import com.jbc.appnetmovie.framework.network.FirebaseHelper
import com.jbc.appnetmovie.presenter.MainActivity
import com.jbc.appnetmovie.util.BaseFragment
import com.jbc.appnetmovie.util.StateView
import com.jbc.appnetmovie.util.hideKeyboard
import com.jbc.appnetmovie.util.initToolbar
import com.jbc.appnetmovie.util.isEmailValid
import com.jbc.appnetmovie.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 03/01/2024.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    R.layout.fragment_login,
    FragmentLoginBinding::bind
) {

    private val viewModel: LoginViewModel by viewModels()

    override fun initUI() {

        binding?.toolbar?.let { initToolbar(it) }

        binding?.btnLoginSignIn?.setOnClickListener { validData() }

        binding?.progress?.let {
            Glide
                .with(requireContext())
                .load(R.drawable.loading)
                .into(it)
        }

        binding?.textForgotLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }
    }

    private fun validData() {
        val email = binding?.editLoginEmail?.text.toString().trim()
        val password = binding?.editLoginPassword?.text.toString().trim()

        if (email.isEmailValid()) {
            if (password.isNotEmpty()) {

                hideKeyboard()
                login(email, password)

            } else {
                binding?.editLoginPassword?.requestFocus()
                showSnackBar(message = R.string.text_password_empty_fragment)
            }
        } else {
            binding?.editLoginEmail?.requestFocus()
            showSnackBar(message = R.string.text_email_empty_fragment)
        }
    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = false
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    showSnackBar(message = FirebaseHelper.validError(stateView.message ?: ""))
                }
            }
        }
    }
}