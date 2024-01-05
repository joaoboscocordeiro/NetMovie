package com.jbc.core.usecase

import com.jbc.core.data.AuthRepository
import javax.inject.Inject


/*
 * Created by Joao Bosco on 04/01/24.
 */
class ForgotUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) {
        return authRepository.forgot(email)
    }
}