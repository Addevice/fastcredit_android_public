package com.app.fastbank.ui.auth.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.fastbank.ui.auth.locator.ServiceLocator
import com.app.fastbank.ui.auth.AuthViewModel
import com.app.fastbank.domain.auth.usecases.AuthUseCase

class ViewModelFactory(private val serviceLocator: ServiceLocator) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AuthViewModel::class.java) ->
                    AuthViewModel(
                        AuthUseCase(
                            serviceLocator.authDataSource
                        ),
                        serviceLocator.getLogger(AuthViewModel::class),
                        serviceLocator.ioDispatcher,
                        serviceLocator.mainDispatcher
                    )

                else -> throw IllegalArgumentException("unknown model class $modelClass")
            }
        } as T
}