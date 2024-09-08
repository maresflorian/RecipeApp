package com.example.recipeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.recipeapp.R
import com.example.recipeapp.adapters.RecipeListAdapter
import com.example.recipeapp.models.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import com.android.volley.Request
import com.example.recipeapp.models.RecipesResponse

class RecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchRecipes()  // Apelează funcția care obține rețetele
    }

    // Funcția care face solicitarea API pentru a obține rețete
    private fun fetchRecipes() {
        val apiKey = "83aae9920d7d43fa8fe53dbf91a8fc3d"
        val url = "https://api.spoonacular.com/recipes/random?number=10&apiKey=$apiKey"
        val requestQueue = Volley.newRequestQueue(requireContext())

        val recipesRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                // Deserializăm direct lista de rețete
                val recipes = Gson().fromJson(response, RecipesResponse::class.java).recipes
                buildRecipesList(recipes)
            },
            { error ->
                // Un mesaj de eroare simplu
                AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Failed to fetch recipes.")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        )

        // Adăugăm cererea în coadă pentru execuție
        requestQueue.add(recipesRequest)
    }



    // Funcția care construiește lista de rețete
    private fun buildRecipesList(recipes: List<RecipeModel>) {
        val layoutManager = LinearLayoutManager(context)
        val adapter = RecipeListAdapter(recipes)
        view?.findViewById<RecyclerView>(R.id.rv_recipes)?.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }
}
