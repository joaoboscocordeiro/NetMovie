package com.jbc.appnetmovie.presenter.auth.register

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentRegisterBinding
import com.jbc.appnetmovie.framework.network.FirebaseHelper
import com.jbc.appnetmovie.presenter.main.activity.MainActivity
import com.jbc.appnetmovie.util.BaseFragment
import com.jbc.appnetmovie.util.StateView
import com.jbc.appnetmovie.util.hideKeyboard
import com.jbc.appnetmovie.util.initToolbar
import com.jbc.appnetmovie.util.isEmailValid
import com.jbc.appnetmovie.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 29/12/2023.
 */
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    R.layout.fragment_register,
    FragmentRegisterBinding::bind
) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun initUI() {

        binding?.toolbar?.let { initToolbar(it) }

        binding?.btnRegisterSignUp?.setOnClickListener { validData() }

        binding?.progress?.let {
            Glide
                .with(requireContext())
                .load(R.drawable.loading)
                .into(it)
        }
    }

    private fun validData() {
        val email = binding?.editRegisterEmail?.text.toString().trim()
        val password = binding?.editRegisterPassword?.text.toString().trim()

        if (email.isEmailValid()) {
            if (password.isNotEmpty()) {

                hideKeyboard()
                register(email, password)

            } else {
                binding?.editRegisterPassword?.requestFocus()
                showSnackBar(message = R.string.text_password_empty_fragment)
            }
        } else {
            binding?.editRegisterEmail?.requestFocus()
            showSnackBar(message = R.string.text_email_empty_fragment)
        }
    }

    private fun register(email: String, password: String) {
        registerViewModel.register(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = true
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