package com.example.learndaggerhilt.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class SettingsManager(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "settings")

    companion object {
        val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    val isLogin = runBlocking {
        dataStore.data.first()[IS_LOGIN]
    }

    suspend fun setLoginSession(isLogin: Boolean){
        dataStore.edit { mutablePreferences ->
            mutablePreferences[IS_LOGIN] = isLogin
        }
    }
}