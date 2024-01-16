package com.project.catalogingmtgcards.core

import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull

suspend fun DataCoordinator.getStringDataStore(): String {
    val context = this.context ?: return defaultStringPreferencesVariable
    return context.dataStore.data.firstOrNull()?.get(PreferencesKey.userUID)
        ?: defaultStringPreferencesVariable
}

suspend fun DataCoordinator.setStringDataStore(value: String) {
    val context = this.context ?: return
    context.dataStore.edit { preferences ->
        preferences[PreferencesKey.userUID] = value
    }
}