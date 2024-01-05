package com.jbc.appnetmovie.presenter.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jbc.appnetmovie.util.StateView
import com.jbc.core.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


/*
 * Created by Joao Bosco on 04/01/24.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun register(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            registerUseCase.invoke(email, password)
            emit(StateView.Success(Unit))
        } catch (ex: Throwable) {
            ex.printStackTrace()
            emit(StateView.Error(ex.message))
        }
    }
}