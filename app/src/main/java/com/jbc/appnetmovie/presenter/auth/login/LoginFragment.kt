package com.jbc.appnetmovie.presenter.auth.login

import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentLoginBinding
import com.jbc.appnetmovie.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 03/01/2024.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    R.layout.fragment_login,
    FragmentLoginBinding::bind
) {

    //private val viewModel: LoginViewModel by viewModels()
}