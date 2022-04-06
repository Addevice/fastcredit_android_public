package com.app.fastbank.ui.userdetail.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.fastbank.domain.userdetail.usecases.UserDetailUseCase
import com.app.fastbank.ui.userdetail.locator.ServiceLocator
import com.app.fastbank.ui.userdetail.UserDetailViewModel

class ViewModelFactory(private val serviceLocator: ServiceLocator) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(UserDetailViewModel::class.java) ->
                    UserDetailViewModel(
                        UserDetailUseCase(
                            serviceLocator.userDataSource
                        ),
                        serviceLocator.getLogger(UserDetailViewModel::class),
                        serviceLocator.ioDispatcher,
                        serviceLocator.mainDispatcher
                    )

                else -> throw IllegalArgumentException("unknown model class $modelClass")
            }
        } as T
}