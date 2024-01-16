package com.project.catalogingmtgcards.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun DataCoordinator.updateString(value: String) {
    this.stringPreferencesVariable = value

    GlobalScope.launch(Dispatchers.Default) {
        setStringDataStore(value)
    }
}