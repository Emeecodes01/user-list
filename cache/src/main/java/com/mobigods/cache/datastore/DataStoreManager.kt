package com.mobigods.cache.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import com.mobigods.cache.utils.constants.CacheConstants
import com.mobigods.domain.repository.cache.IDataStoreManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject



class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context): IDataStoreManager {

    private val dataStore: DataStore<Preferences> by lazy { context.createDataStore(name = CacheConstants.SHARED_PREFERENCE_NAME) }


    /**
     * Never call this code on the UI thread
     */
    override var isUserListSetUp: Boolean
        get() = runBlocking { dataStore.data.map { value: Preferences -> value[SETUP_DS_KEY] ?: false }.first() }
        set(value) { runBlocking { dataStore.edit { setting -> setting[SETUP_DS_KEY] = value }} }


    companion object {
        val SETUP_DS_KEY = booleanPreferencesKey("is-set-up")
    }
}