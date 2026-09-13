package com.recipe.app.domain.model

data class Recipe(
    val id: String,
    val name: String,
    val image: String?,
    val category: String?
)

data class RecipeDetail(
    val id: String,
    val name: String,
    val image: String?,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val tags: String?,
    val youtubeUrl: String?,
    val sourceUrl: String?
)
