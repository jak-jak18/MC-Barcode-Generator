package com.my.microcenterbarcode.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val username: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadUser()
        }

    }

    fun getUsers(): LiveData<List<String>> {
        return username
    }

    private fun loadUser() {
        username.value = listOf("First", "Second")
    }
    val userName = "test"
    fun getuserName() : String{return userName}
}