package com.recipe.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipe.app.domain.model.Recipe
import com.recipe.app.domain.model.RecipeDetail
import com.recipe.app.domain.usecase.GetRecipeDetailUseCase
import com.recipe.app.domain.usecase.GetRecipesByCategoryUseCase
import com.recipe.app.domain.usecase.SearchRecipesUseCase
import com.recipe.app.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val getRecipesByCategoryUseCase: GetRecipesByCategoryUseCase,
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> = _recipeList

    private val _recipeDetail = MutableLiveData<RecipeDetail>()
    val recipeDetail: LiveData<RecipeDetail> = _recipeDetail

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = searchRecipesUseCase(query)) {
                is Result.Success -> {
                    _recipeList.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _error.value = result.exception.message
                    _recipeList.value = emptyList()
                }
                is Result.Loading -> {}
            }
            _isLoading.value = false
        }
    }

    fun getRecipesByCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = getRecipesByCategoryUseCase(category)) {
                is Result.Success -> {
                    _recipeList.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _error.value = result.exception.message
                    _recipeList.value = emptyList()
                }
                is Result.Loading -> {}
            }
            _isLoading.value = false
        }
    }

    fun getRecipeDetail(recipeId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = getRecipeDetailUseCase(recipeId)) {
                is Result.Success -> {
                    _recipeDetail.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _error.value = result.exception.message
                }
                is Result.Loading -> {}
            }
            _isLoading.value = false
        }
    }
}
