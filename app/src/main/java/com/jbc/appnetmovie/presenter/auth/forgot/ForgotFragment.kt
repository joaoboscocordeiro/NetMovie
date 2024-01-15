package com.jbc.appnetmovie.presenter.auth.forgot

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentForgotBinding
import com.jbc.appnetmovie.util.BaseFragment
import com.jbc.appnetmovie.util.StateView
import com.jbc.appnetmovie.util.hideKeyboard
import com.jbc.appnetmovie.util.initToolbar
import com.jbc.appnetmovie.util.isEmailValid
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 04/01/2024.
 */
@AndroidEntryPoint
class ForgotFragment : BaseFragment<FragmentForgotBinding>(
    R.layout.fragment_forgot,
    FragmentForgotBinding::bind
) {

    private val viewModel: ForgotViewModel by viewModels()

    override fun initUI() {

        binding?.toolbar?.let { initToolbar(it) }

        binding?.btnForgotNext?.setOnClickListener { validData() }

        binding?.progress?.let {
            Glide
                .with(requireContext())
                .load(R.drawable.loading)
                .into(it)
        }
    }

    private fun validData() {
        val email = binding?.editForgotEmail?.text.toString().trim()

        if (email.isEmailValid()) {

            hideKeyboard()
            forgot(email)

        } else {
            binding?.editForgotEmail?.requestFocus()
            Toast.makeText(requireContext(), "E-mail inválido.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun forgot(email: String) {
        viewModel.forgot(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Error -> {
                    binding?.progress?.isVisible = true
                }

                is StateView.Loading -> {
                    binding?.progress?.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "E-mail enviado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "ERRO ao enviar e-mail!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}