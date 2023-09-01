package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.catalogingmtgcards.data.repository.ScryFallRepositoryImpl
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase
import com.project.catalogingmtgcards.domain.useCase.ScryFallUseCase
import kotlinx.coroutines.launch

class ScryFallViewModel(
    application: Application,
    private val useCase: ScryFallUseCase
    ): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val state: MutableLiveData<ScryFallViewModelState> = MutableLiveData()
    val getState: LiveData<ScryFallViewModelState> = state

    fun searchCard(){
        viewModelScope.launch {
            val e = useCase.getListCard("color:red cmc=1")
            Log.d("testeeee", e.toString())
            //state.postValue(ScryFallViewModelState.Success(e))
        }
    }
}

