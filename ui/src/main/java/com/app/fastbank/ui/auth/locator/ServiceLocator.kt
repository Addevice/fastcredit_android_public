package com.app.fastbank.ui.auth.locator

import com.app.fastbank.common.Logger
import com.app.fastbank.domain.auth.interfaces.AuthDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.reflect.KClass

interface ServiceLocator {

    val authDataSource: AuthDataSource

    val ioDispatcher: CoroutineDispatcher

    val mainDispatcher: CoroutineDispatcher

    fun getLogger(cls: KClass<*>): Logger
}