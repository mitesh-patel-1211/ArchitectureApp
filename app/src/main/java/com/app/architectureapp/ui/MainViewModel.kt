package com.app.architectureapp.ui

import androidx.lifecycle.ViewModel
import com.app.architectureapp.repository.LocalRepository
import com.app.architectureapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var testString = "testString"

}