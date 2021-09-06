package ch.teko.rezept_app.ui.home

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ch.teko.rezept_app.R
import ch.teko.rezept_app.ui.DetailRecipe
import ch.teko.rezept_app.web.TastyRecipeAdapter
import ch.teko.rezept_app.web.TastyRecipeApiConsumer
import com.squareup.picasso.Picasso

class RecipeDetailActivity : AppCompatActivity(), TastyRecipeApiConsumer {
    private val tastyAdapter = TastyRecipeAdapter(this)
    private lateinit var instructions: TextView
    private lateinit var sections: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        instructions = findViewById(R.id.instructions)
        sections = findViewById(R.id.sections)

        tastyAdapter.readRecipe(intent.extras?.getInt("KEY_ID")
                ?: throw RuntimeException("Keine ID im Intent vorhanden."))
    }

    override fun onFailure(message: String) {
        this.runOnUiThread {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun readRescipeResponse(recipe: DetailRecipe) {
        this.runOnUiThread {
            findViewById<TextView>(R.id.name).text = recipe.name
            Picasso.with(applicationContext).load(recipe.thumbnail_url).resize(100,100).into(findViewById<ImageView>(R.id.thumbnail_url))
            instructions.text = recipe.instructionAsText()
            sections.adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, recipe.allComponents().map { it.raw_text })
        }
    }
}
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup)=
//            LayoutInflater.from(context).inflate(R.layout.activity_recipe_detail, parent, false).also {
//                it.findViewById<TextView>(R.id.name).text = getItem(position)?.name
//                it.findViewById<TextView>(R.id.sections).text = getItem(position)?.name
//                it.findViewById<TextView>(R.id.sections).text = getItem(position)?.name
//                Picasso.with(context).load(getItem(position)?.thumbnail_url).resize(100,100).into(it.findViewById<ImageView>(R.id.RecipeImg))
//                // it.findViewById<ImageView>(R.id.RecipeImg).setImageBitmap(getItem(position)?.getBitmap())
//
//            }
//    }

