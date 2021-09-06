package ch.teko.rezept_app.web

import ch.teko.rezept_app.ui.DetailRecipe
import ch.teko.rezept_app.ui.Recipe
import com.beust.klaxon.Klaxon
import okhttp3.*
import java.io.IOException
import java.io.StringReader


class TastyRecipeAdapter(
        private val consumer: TastyRecipeApiConsumer
) : BaseAdapter() {

    private val client = OkHttpClient()

    fun readRecipe(id: Int) {
        val url = HttpUrl.Builder()
                .scheme(SHEMA)
                .host(HOST)
                .addPathSegment(RECIPIES_SEGMET)
                .addPathSegment(DETAIL_SEGMENT)
                .addQueryParameter(ID, id.toString())
                .build()

        client.newCall(buildRequest(url)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                consumer.onFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Klaxon().apply {
                    val json = this.parseJsonObject(StringReader(response.body?.string()))
                    consumer.readRescipeResponse(this.parseFromJsonObject<DetailRecipe>(json)
                            ?: throw RuntimeException("Resopnse konnte nicht geparsed werden."))
                }
            }
        })
    }

}

interface TastyRecipeApiConsumer {
    fun onFailure(message: String)
    fun readRescipeResponse(recipe: DetailRecipe)
}
