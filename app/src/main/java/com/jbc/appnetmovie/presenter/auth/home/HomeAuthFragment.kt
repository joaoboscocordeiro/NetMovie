package com.jbc.appnetmovie.presenter.auth.home

import androidx.navigation.fragment.findNavController
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentHomeAuthBinding
import com.jbc.appnetmovie.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 28/12/2023.
 */
@AndroidEntryPoint
class HomeAuthFragment : BaseFragment<FragmentHomeAuthBinding>(
    R.layout.fragment_home_auth,
    FragmentHomeAuthBinding::bind
) {

    override fun initUI() {
        binding?.btnSignInWithPassword?.setOnClickListener {
            findNavController().navigate(R.id.action_homeAuthFragment_to_loginFragment)
        }
        binding?.textSignUp?.setOnClickListener {
            findNavController().navigate(R.id.action_homeAuthFragment_to_registerFragment)
        }
    }
}