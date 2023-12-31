package com.jbc.appnetmovie.presenter.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentRegisterBinding
import com.jbc.appnetmovie.util.BaseFragment
import com.jbc.appnetmovie.util.StateView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
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

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {

                register(email, password)

            } else {
                binding?.editRegisterPassword?.requestFocus()
            }
        } else {
            binding?.editRegisterEmail?.requestFocus()
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
                    Toast.makeText(
                        requireContext(),
                        "Usuário cadastrado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "ERRO ao cadastrar usuário!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}