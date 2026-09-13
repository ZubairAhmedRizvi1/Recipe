package com.recipe.app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeListResponse(
    @SerializedName("meals")
    val meals: List<RecipeDto>?
)

data class RecipeDetailResponse(
    @SerializedName("meals")
    val meals: List<RecipeDetailDto>?
)

data class RecipeDto(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val image: String?,
    @SerializedName("strCategory")
    val category: String?
)

data class RecipeDetailDto(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val image: String?,
    @SerializedName("strCategory")
    val category: String?,
    @SerializedName("strArea")
    val area: String?,
    @SerializedName("strInstructions")
    val instructions: String?,
    @SerializedName("strTags")
    val tags: String?,
    @SerializedName("strYoutube")
    val youtubeUrl: String?,
    @SerializedName("strSource")
    val sourceUrl: String?
)
