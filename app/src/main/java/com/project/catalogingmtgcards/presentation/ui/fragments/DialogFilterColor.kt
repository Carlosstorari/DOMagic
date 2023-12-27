package com.project.catalogingmtgcards.presentation.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.DialogFilterColorBinding
import com.project.catalogingmtgcards.presentation.ui.fragments.SearchConstants.ONLY_BLACK
import com.project.catalogingmtgcards.presentation.ui.fragments.SearchConstants.ONLY_BLUE
import com.project.catalogingmtgcards.presentation.ui.fragments.SearchConstants.ONLY_GREEN
import com.project.catalogingmtgcards.presentation.ui.fragments.SearchConstants.ONLY_RED
import com.project.catalogingmtgcards.presentation.ui.fragments.SearchConstants.ONLY_WHITE

class DialogFilterColor(private val context: Context) {

    fun showDialog(whenConfirmIsClicked: (query: String) -> Unit) {
        DialogFilterColorBinding.inflate(LayoutInflater.from(context)).apply {
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val query = onRadioButtonClicked(filterRadioGroup)
                    whenConfirmIsClicked(query)
                }.show()
        }
    }

    fun onRadioButtonClicked(view: View): String {
        if (view is RadioGroup) {
            return when (view.checkedRadioButtonId) {
                R.id.btn_radio_white -> ONLY_WHITE

                R.id.btn_radio_red -> ONLY_RED

                R.id.btn_radio_green -> ONLY_GREEN

                R.id.btn_radio_blue -> ONLY_BLUE

                R.id.btn_radio_black -> ONLY_BLACK


                else -> ""
            }
        }
        return ""
    }

}