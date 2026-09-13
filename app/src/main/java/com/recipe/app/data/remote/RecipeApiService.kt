package com.recipe.app.data.remote

import com.recipe.app.data.remote.dto.RecipeListResponse
import com.recipe.app.data.remote.dto.RecipeDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {

    @GET("search.php")
    suspend fun searchRecipes(@Query("s") searchQuery: String): RecipeListResponse

    @GET("list.php")
    suspend fun getRecipeCategories(@Query("c") list: String): RecipeListResponse

    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") category: String): RecipeListResponse

    @GET("lookup.php")
    suspend fun getRecipeDetail(@Query("i") recipeId: String): RecipeDetailResponse
}
