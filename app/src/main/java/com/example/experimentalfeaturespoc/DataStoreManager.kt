package com.example.experimentalfeaturespoc

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

object DataStoreManager{

    private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
        name = "MUTLIPLELANG"
    )

    suspend fun putString(key: String, value: String) {
        val datastoreKey = stringPreferencesKey(key)
        MyApplication.appContext!!.dataStore.edit {
            it[datastoreKey] = value
        }
    }

    fun getString(key: String): Flow<String?> {
        val dataStoreKey = stringPreferencesKey(key)

        return MyApplication.appContext!!.dataStore.data.map { preferences ->
            preferences[dataStoreKey]
        }
    }

}