package com.jbc.appnetmovie.presenter.auth.forgot

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentForgotBinding
import com.jbc.appnetmovie.framework.network.FirebaseHelper
import com.jbc.appnetmovie.util.BaseFragment
import com.jbc.appnetmovie.util.StateView
import com.jbc.appnetmovie.util.hideKeyboard
import com.jbc.appnetmovie.util.initToolbar
import com.jbc.appnetmovie.util.isEmailValid
import com.jbc.appnetmovie.util.showSnackBar
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
            showSnackBar(message = R.string.text_email_empty_fragment)
        }
    }

    private fun forgot(email: String) {
        viewModel.forgot(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding?.progress?.isVisible = false
                }

                is StateView.Success -> {
                    binding?.progress?.isVisible = false
                    showSnackBar(message = R.string.text_send_email_success_forget_fragment)
                }

                is StateView.Error -> {
                    binding?.progress?.isVisible = true
                    showSnackBar(message = FirebaseHelper.validError(stateView.message ?: ""))
                }
            }
        }
    }
}