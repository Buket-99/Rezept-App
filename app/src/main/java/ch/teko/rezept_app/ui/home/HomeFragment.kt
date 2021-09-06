package ch.teko.rezept_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ch.teko.rezept_app.R
import ch.teko.rezept_app.ui.Recipe
import ch.teko.rezept_app.web.TastyRecipiesAdapter
import ch.teko.rezept_app.web.TastyRecipiesApiConsumer

class HomeFragment : Fragment(), TastyRecipiesApiConsumer {

    val tastyAdapter = TastyRecipiesAdapter(this)
    lateinit var root: View
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_home, container, false)
        val searchField = root.findViewById<EditText>(R.id.Searchfield)
        val searchButton = root.findViewById<Button>(R.id.SearchButton)
        searchButton.setOnClickListener {
            tastyAdapter.readRecipes(20, searchField.text.toString())
        }
        return root
    }


    override fun onFailure(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun readRescipesResponse(recipes: List<Recipe>) {
        val recipelist = root.findViewById<ListView>(R.id.RecipeList)
        activity?.runOnUiThread {
            recipelist.adapter = RecipeAdapter(context, recipes)
            recipelist.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context, RecipeDetailActivity::class.java)
                intent.putExtra("KEY_ID", (parent.adapter.getItem(position) as Recipe).id)
                startActivity(intent)
            }
        }
    }

}



