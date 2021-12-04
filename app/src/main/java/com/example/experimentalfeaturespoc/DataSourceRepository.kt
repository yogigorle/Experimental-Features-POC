package com.example.experimentalfeaturespoc

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.createDataStore

class DataSourceRepository(context: Context) {

    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences> = context.createDataStore(name = "LanguageData")

}