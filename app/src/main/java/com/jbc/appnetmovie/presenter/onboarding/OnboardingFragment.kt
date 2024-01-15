package com.jbc.appnetmovie.presenter.onboarding

import androidx.navigation.fragment.findNavController
import com.jbc.appnetmovie.R
import com.jbc.appnetmovie.databinding.FragmentOnboardingBinding
import com.jbc.appnetmovie.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/*
 * Created by Joao Bosco on 27/12/2023.
 */
@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(
    R.layout.fragment_onboarding,
    FragmentOnboardingBinding::bind
) {

    override fun initUI() {
        binding?.btnStart?.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_authentication)
        }
    }
}