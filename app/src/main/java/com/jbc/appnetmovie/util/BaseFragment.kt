package com.jbc.appnetmovie.util

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


/*
 * Created by Joao Bosco on 04/01/24.
 */
abstract class BaseFragment<T>(
    @LayoutRes layoutId: Int,
    val bind: (View) -> T
) : Fragment(layoutId) {

    protected var binding: T? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)

        initUI()
    }

    abstract fun initUI()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}