package com.jbc.appnetmovie.presenter.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentLoginBinding
import com.jbc.appnetmovie.util.BaseFragment
import com.jbc.appnetmovie.util.StateView
import com.jbc.appnetmovie.util.hideKeyboard
import com.jbc.appnetmovie.util.isEmailValid
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding?.btnLoginSignIn?.setOnClickListener { validData() }

        binding?.progress?.let {
            Glide
                .with(requireContext())
                .load(R.drawable.loading)
                .into(it)
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
            }
        } else {
            binding?.editLoginEmail?.requestFocus()
            Toast.makeText(requireContext(), "E-mail inválido.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Error -> {
                    binding?.progress?.isVisible = true
                }
                is StateView.Loading -> {
                    binding?.progress?.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "Usuário logado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "ERRO ao logar usuário!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}