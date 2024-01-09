package com.project.catalogingmtgcards.data.repository

import androidx.annotation.StringRes

class Resource<T>(val content: T, @StringRes val error: Int? = null)