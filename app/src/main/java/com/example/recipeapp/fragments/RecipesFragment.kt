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

// Fragment pentru afișarea listei de rețete
class RecipesFragment : Fragment() {

    // Creează și returnează layout-ul pentru acest fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    // După ce view-ul a fost creat, apelează funcția de fetch pentru rețete
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchRecipes()  // Apelează funcția care obține rețetele de la API
    }

    // Funcția care face solicitarea API pentru a obține rețete
    private fun fetchRecipes() {
        val apiKey = "83aae9920d7d43fa8fe53dbf91a8fc3d"  // Cheia API pentru accesarea Spoonacular API
        val url = "https://api.spoonacular.com/recipes/random?number=10&apiKey=$apiKey"  // URL pentru a obține 10 rețete random
        val requestQueue = Volley.newRequestQueue(requireContext())  // Coada de solicitări folosind Volley

        // Crearea unei solicitări GET folosind StringRequest
        val recipesRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                // În cazul în care solicitarea are succes, deserializăm răspunsul JSON în lista de rețete
                val recipes = Gson().fromJson(response, RecipesResponse::class.java).recipes
                buildRecipesList(recipes)  // Construim lista de rețete pe baza datelor obținute
            },
            { error ->
                // În caz de eroare la solicitare, afișăm un dialog de eroare
                AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Failed to fetch recipes.")  // Mesaj de eroare
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }  // Închidem dialogul la click pe OK
                    .show()
            }
        )

        // Adăugăm cererea în coada de execuție
        requestQueue.add(recipesRequest)
    }

    // Funcția care construiește lista de rețete și configurează RecyclerView
    private fun buildRecipesList(recipes: List<RecipeModel>) {
        // Inițializăm un LayoutManager pentru a aranja item-urile în RecyclerView
        val layoutManager = LinearLayoutManager(context)
        // Cream un adapter pentru RecyclerView pe baza listei de rețete
        val adapter = RecipeListAdapter(recipes)
        // Referință la RecyclerView și setarea layout manager-ului și a adapter-ului
        view?.findViewById<RecyclerView>(R.id.rv_recipes)?.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }
}
