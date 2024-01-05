package com.jbc.appnetmovie.framework.di

import com.jbc.appnetmovie.framework.remote.FirebaseAuthDataSourceImpl
import com.jbc.core.data.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/*
 * Created by Joao Bosco on 04/01/24.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindAuthDataSource(dataSource: FirebaseAuthDataSourceImpl): AuthRepository
}