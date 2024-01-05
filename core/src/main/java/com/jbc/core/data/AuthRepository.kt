package com.jbc.core.data


/*
 * Created by Joao Bosco on 04/01/24.
 */
interface AuthRepository {
 suspend fun login(email: String, password: String)
 suspend fun register(email: String, password: String)
 suspend fun forgot(email: String)
}