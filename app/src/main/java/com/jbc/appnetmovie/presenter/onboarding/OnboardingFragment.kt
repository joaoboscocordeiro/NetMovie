package com.jbc.appnetmovie.presenter.onboarding

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        TODO("Not yet implemented")
    }
}