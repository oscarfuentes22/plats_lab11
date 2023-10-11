package com.zezzi.eventzezziapp.ui.meals.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zezzi.eventzezziapp.data.repository.MealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {

    private val _categories = MutableStateFlow(MealsCategoriUiState(emptyList()))
    val categories: StateFlow<MealsCategoriUiState> = _categories

    fun getMeals() {
        viewModelScope.launch {
            _categories.update {
                MealsCategoriUiState(
                    categories = repository.getMeals().categories
                )
            }
        }
    }
}