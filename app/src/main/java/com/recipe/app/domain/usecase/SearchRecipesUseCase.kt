package com.recipe.app.domain.usecase

import com.recipe.app.domain.model.Recipe
import com.recipe.app.domain.repository.RecipeRepository
import com.recipe.app.domain.util.Result
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(query: String): Result<List<Recipe>> {
        return repository.searchRecipes(query)
    }
}
