package com.example.kaizentest.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kaizentest.repository.SportsRepository
import com.example.kaizentest.viewModel.SportsViewModel
import java.lang.IllegalArgumentException

class SportsViewModelFactory(private val repository: SportsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SportsViewModel::class.java)) {
            return SportsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}