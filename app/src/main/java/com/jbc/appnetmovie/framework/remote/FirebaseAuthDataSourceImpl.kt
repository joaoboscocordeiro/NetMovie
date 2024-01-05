package com.jbc.appnetmovie.framework.remote

import com.google.firebase.auth.FirebaseAuth
import com.jbc.core.data.AuthRepository
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine


/*
 * Created by Joao Bosco on 04/01/24.
 */
class FirebaseAuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    task.exception?.let { error ->
                        continuation.resumeWith(Result.failure(error))
                    }
                }
            }
        }
    }

    override suspend fun register(email: String, password: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { error ->
                            continuation.resumeWith(Result.failure(error))
                        }
                    }
                }
        }
    }

    override suspend fun forgot(email: String) {
        return suspendCoroutine { continuation ->
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    task.exception?.let { error ->
                        continuation.resumeWith(Result.failure(error))
                    }
                }
            }
        }
    }
}