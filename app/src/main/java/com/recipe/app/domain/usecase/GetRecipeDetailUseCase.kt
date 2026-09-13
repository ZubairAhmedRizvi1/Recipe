package com.recipe.app.domain.usecase

import com.recipe.app.domain.model.RecipeDetail
import com.recipe.app.domain.repository.RecipeRepository
import com.recipe.app.domain.util.Result
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: String): Result<RecipeDetail> {
        return repository.getRecipeDetail(recipeId)
    }
}
