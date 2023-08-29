package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModelState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ScryFallViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getState.observe(this, Observer {
            when(it) {
               is ScryFallViewModelState.Success -> {
                   Toast.makeText(this,
                       "",
                       Toast.LENGTH_LONG).show()
               }
            }
        })
        viewModel.searchCard()
    }

}