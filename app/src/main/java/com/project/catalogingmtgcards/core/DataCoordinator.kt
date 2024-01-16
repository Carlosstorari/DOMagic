package com.project.catalogingmtgcards.core

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class DataCoordinator {
    companion object {
        val shared = DataCoordinator()
        const val identifier = "[DataCoordinator]"
    }

    var context: Context? = null

    var stringPreferencesVariable: String = ""
    val defaultStringPreferencesVariable = ""

    private val USER_PREFERENCES_NAME = "user_preferences"
    val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    fun initialize(context:Context, onLoad: () -> Unit) {
        this.context = context

        GlobalScope.launch(Dispatchers.Default) {
            stringPreferencesVariable = getStringDataStore()
        }

        onLoad
    }
}