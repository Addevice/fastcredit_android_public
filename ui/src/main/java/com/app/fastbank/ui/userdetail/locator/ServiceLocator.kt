package com.app.fastbank.ui.userdetail.locator

import com.app.fastbank.common.Logger
import com.app.fastbank.data.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.reflect.KClass

interface ServiceLocator {

    val userDataSource: UserDataSource

    val ioDispatcher: CoroutineDispatcher

    val mainDispatcher: CoroutineDispatcher

    fun getLogger(cls: KClass<*>): Logger
}