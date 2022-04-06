package com.app.fastbank.ui.userdetail.locator

import android.content.Context
import androidx.preference.PreferenceManager
import com.app.fastbank.data.UserDataSource
import kotlinx.coroutines.Dispatchers
import logger.AndroidLogger
import kotlin.reflect.KClass

class DefaultServiceLocator private constructor(private val applicationContext: Context):ServiceLocator{

    companion object {

        private var instance: DefaultServiceLocator? = null

        fun getInstance(applicationContext: Context) = instance ?: DefaultServiceLocator(
            applicationContext
        ).also { instance = it }
    }

    override val userDataSource by lazy { UserDataSource(PreferenceManager.getDefaultSharedPreferences(applicationContext)) }

    override val ioDispatcher = Dispatchers.IO

    override val mainDispatcher = Dispatchers.Main.immediate

    private val loggers = mutableMapOf<KClass<*>, AndroidLogger>()

    override fun getLogger(cls: KClass<*>) = loggers[cls] ?: AndroidLogger(cls).also { loggers[cls] = it }
}