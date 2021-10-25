package com.rabstract.gifapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommonViewModel : ViewModel() {

    val isRefreshRequired = MutableLiveData<Boolean>()

    fun setRefreshRequired(required: Boolean) {

        isRefreshRequired.value = required
    }
}