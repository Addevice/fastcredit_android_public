package com.app.fastbank.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fastbank.ui.auth.common.Event
import com.app.fastbank.common.Logger
import com.app.fastbank.domain.auth.usecases.AuthUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val logger: Logger,
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val success = MutableLiveData<String>()
    val shouldShowLoadingIndicator = MutableLiveData<Boolean>()
    val showErrorToastEvent = MutableLiveData<Event<Unit>>()

    fun onLoginButtonClick() = viewModelScope.launch(mainDispatcher) {
        shouldShowLoadingIndicator.value = true

        viewModelScope.launch(ioDispatcher) {
            try {
                if (email.value != null && password.value != null){
                    authUseCase(email.value!!, password.value!!)
                    launch(mainDispatcher) {
                        success.value = "Success"
                    }
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