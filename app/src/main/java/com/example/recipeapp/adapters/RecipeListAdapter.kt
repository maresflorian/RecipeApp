package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.models.RecipeModel

// Adapter pentru RecyclerView care afișează o listă de rețete.
class RecipeListAdapter(private val recipesList: List<RecipeModel>) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    // Returnează numărul de elemente din lista de rețete.
    override fun getItemCount() = recipesList.size

    // Creează un nou ViewHolder atunci când RecyclerView are nevoie de unul nou.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        // Obține LayoutInflater pentru a infla layout-ul unui item de listă.
        val inflater = LayoutInflater.from(parent.context)
        // Inflează layout-ul pentru un item din RecyclerView (recipe_item.xml).
        val view = inflater.inflate(R.layout.recipe_item, parent, false)
        // Returnează un nou ViewHolder care conține acest view.
        return RecipeViewHolder(view)
    }

    // Leagă datele (rețeta) de ViewHolder pentru o poziție specifică în listă.
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        // Obține rețeta de la poziția curentă, dacă există, și o leagă de ViewHolder.
        recipesList.getOrNull(position)?.let { holder.bind(it) }
    }

    // ViewHolder intern care gestionează afișarea unui item de tip rețetă.
    inner class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        // Referințe la TextView-urile din layout care vor afișa titlul și descrierea rețetei.
        private val recipeNameTextView: TextView = view.findViewById(R.id.tv_recipe_name)
        private val recipeDescriptionTextView: TextView = view.findViewById(R.id.tv_recipe_description)

        // Metodă pentru a lega datele modelului RecipeModel de UI.
        fun bind(model: RecipeModel) {
            // Setează titlul rețetei în TextView-ul corespunzător.
            recipeNameTextView.text = model.title
            // Setează instrucțiunile rețetei în TextView-ul corespunzător.
            recipeDescriptionTextView.text = model.instructions
        }
    }
}
