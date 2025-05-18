package com.example.transportediana.viewmodel

import com.example.transportediana.clases.AutobusconRuta

sealed class UiState {
    object Loading : UiState()
    data class Success(val buses: List<AutobusconRuta>) : UiState()
    data class Error(val message: String) : UiState()
}
