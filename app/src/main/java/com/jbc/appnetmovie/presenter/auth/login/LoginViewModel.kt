package com.jbc.appnetmovie.presenter.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jbc.appnetmovie.util.StateView
import com.jbc.core.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


/*
 * Created by Joao Bosco on 04/01/24.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            authUseCase(email, password)
            emit(StateView.Success(null))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}