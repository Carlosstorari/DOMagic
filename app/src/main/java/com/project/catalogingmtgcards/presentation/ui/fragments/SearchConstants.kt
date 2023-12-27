package com.project.catalogingmtgcards.presentation.ui.fragments

object SearchConstants {
    const val ONLY_RED = "color>=r -c:u -c:g -c:b -c:w"
    const val ONLY_BLUE = "color>=u -c:r -c:g -c:b -c:w"
    const val ONLY_GREEN = "color>=g -c:u -c:r -c:b -c:w"
    const val ONLY_BLACK = "color>=b -c:u -c:g -c:r -c:w"
    const val ONLY_WHITE = "color>=w -c:u -c:g -c:b -c:r"
}