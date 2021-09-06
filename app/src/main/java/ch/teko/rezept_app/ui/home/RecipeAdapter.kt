package ch.teko.rezept_app.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import ch.teko.rezept_app.R
import ch.teko.rezept_app.ui.Recipe
import com.squareup.picasso.Picasso

const val KEY = "KEY"

class RecipeAdapter(context: Context?, objects: List<Recipe>) :
        ArrayAdapter<Recipe>(context!!, R.layout.recipe_overview,objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup)=
        LayoutInflater.from(context).inflate(R.layout.recipe_overview, parent, false).also {
        it.findViewById<TextView>(R.id.RecipeName).text = getItem(position)?.name
            Picasso.with(context).load(getItem(position)?.thumbnail_url).resize(100,100).into(it.findViewById<ImageView>(R.id.RecipeImg))
           // it.findViewById<ImageView>(R.id.RecipeImg).setImageBitmap(getItem(position)?.getBitmap())

    }

}