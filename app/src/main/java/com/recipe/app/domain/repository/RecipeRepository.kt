package com.recipe.app.domain.repository

import com.recipe.app.domain.model.Recipe
import com.recipe.app.domain.model.RecipeDetail
import com.recipe.app.domain.util.Result

interface RecipeRepository {
    suspend fun searchRecipes(query: String): Result<List<Recipe>>
    suspend fun getRecipesByCategory(category: String): Result<List<Recipe>>
    suspend fun getRecipeDetail(recipeId: String): Result<RecipeDetail>
}
