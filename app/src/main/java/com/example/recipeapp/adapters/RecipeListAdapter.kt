package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.models.RecipeModel

class RecipeListAdapter(private val recipesList: List<RecipeModel>) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun getItemCount() = recipesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        recipesList.getOrNull(position)?.let { holder.bind(it) }
    }

    inner class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val recipeNameTextView: TextView = view.findViewById(R.id.tv_recipe_name)
        private val recipeDescriptionTextView: TextView = view.findViewById(R.id.tv_recipe_description)

        fun bind(model: RecipeModel) {
            recipeNameTextView.text = model.title
            recipeDescriptionTextView.text = model.instructions
        }
    }
}
