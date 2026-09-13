package com.recipe.app.data.repository

import com.recipe.app.data.remote.RecipeApiService
import com.recipe.app.domain.model.Recipe
import com.recipe.app.domain.model.RecipeDetail
import com.recipe.app.domain.repository.RecipeRepository
import com.recipe.app.domain.util.Result
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: RecipeApiService
) : RecipeRepository {

    override suspend fun searchRecipes(query: String): Result<List<Recipe>> {
        return try {
            val response = apiService.searchRecipes(query)
            val recipes = response.meals?.map { dto ->
                Recipe(
                    id = dto.id,
                    name = dto.name,
                    image = dto.image,
                    category = dto.category
                )
            } ?: emptyList()
            Result.Success(recipes)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getRecipesByCategory(category: String): Result<List<Recipe>> {
        return try {
            val response = apiService.getRecipesByCategory(category)
            val recipes = response.meals?.map { dto ->
                Recipe(
                    id = dto.id,
                    name = dto.name,
                    image = dto.image,
                    category = dto.category
                )
            } ?: emptyList()
            Result.Success(recipes)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getRecipeDetail(recipeId: String): Result<RecipeDetail> {
        return try {
            val response = apiService.getRecipeDetail(recipeId)
            val detail = response.meals?.firstOrNull()?.let { dto ->
                RecipeDetail(
                    id = dto.id,
                    name = dto.name,
                    image = dto.image,
                    category = dto.category,
                    area = dto.area,
                    instructions = dto.instructions,
                    tags = dto.tags,
                    youtubeUrl = dto.youtubeUrl,
                    sourceUrl = dto.sourceUrl
                )
            }
            if (detail != null) {
                Result.Success(detail)
            } else {
                Result.Error(Exception("Recipe not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
