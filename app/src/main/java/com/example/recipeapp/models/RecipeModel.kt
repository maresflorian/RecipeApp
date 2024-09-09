package com.example.recipeapp.models

data class RecipeModel(
    val id: Int, // ID-ul unic al rețetei, primit de la un API
    val title: String, // Titlul rețetei, care reprezintă numele acesteia
    val image: String, // URL-ul imaginii asociate rețetei
    val instructions: String // Instrucțiunile pentru prepararea rețetei
)
