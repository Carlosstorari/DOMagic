package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.catalogingmtgcards.data.model.CardList
import com.project.catalogingmtgcards.domain.repository.ScryFallRepository
import kotlinx.coroutines.launch

class ScryFallViewModel(
    application: Application,
    private val repository: ScryFallRepository
    ): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val state: MutableLiveData<ScryFallViewModelState> = MutableLiveData()
    val getState: LiveData<ScryFallViewModelState> = state

    fun searchCard(){
        viewModelScope.launch {
            val e = repository.buscaCard("color:red cmc=1")
            Log.d("testeeee", e.toString())
            //state.postValue(ScryFallViewModelState.Success(e))
        }
    }
}

sealed class ScryFallViewModelState{
    data class Success(val card: CardList): ScryFallViewModelState()
    object Error: ScryFallViewModelState()// é object pq nao é passado parametro
    object Empty: ScryFallViewModelState()
    object Loading: ScryFallViewModelState()
}
