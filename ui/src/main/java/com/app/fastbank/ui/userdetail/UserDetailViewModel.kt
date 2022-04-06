package com.app.fastbank.ui.userdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fastbank.domain.userdetail.usecases.UserDetailUseCase
import com.app.fastbank.common.Logger
import com.app.fastbank.domain.userdetail.models.User
import com.app.fastbank.ui.auth.common.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserDetailViewModel(
    val userDetailUseCase: UserDetailUseCase,
    private val logger: Logger,
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher
):ViewModel() {

    val user = MutableLiveData<User>()
    val shouldShowLoadingIndicator = MutableLiveData<Boolean>()
    val showErrorToastEvent = MutableLiveData<Event<Unit>>()

    fun loadUser(){
        shouldShowLoadingIndicator.value = true

        viewModelScope.launch(ioDispatcher) {
            try {
                val user = userDetailUseCase()
                launch(mainDispatcher) {
                   this@UserDetailViewModel.user.value = user
                }
            } catch (e: Exception) {
                logger.warn("An error occurred while login", e)

                launch(mainDispatcher) {
                    showErrorToastEvent.value = Event(Unit)
                }
            }

            launch(mainDispatcher) {
                shouldShowLoadingIndicator.value = false
            }
        }
    }

}