package com.recipe.app.domain.usecase

import com.recipe.app.domain.model.Recipe
import com.recipe.app.domain.repository.RecipeRepository
import com.recipe.app.domain.util.Result
import javax.inject.Inject

class GetRecipesByCategoryUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(category: String): Result<List<Recipe>> {
        return repository.getRecipesByCategory(category)
    }
}
