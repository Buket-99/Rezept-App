package ch.teko.rezept_app.web


import ch.teko.rezept_app.ui.Recipe
import com.beust.klaxon.Klaxon
import okhttp3.*
import java.io.IOException
import java.io.StringReader


class TastyRecipiesAdapter(
        private val consumer: TastyRecipiesApiConsumer
) : BaseAdapter() {

    private val client = OkHttpClient()

    fun readRecipes(size: Int = 10, filter: String) {
        val url = HttpUrl.Builder()
            .scheme(SHEMA)
            .host(HOST)
                .addPathSegment(RECIPIES_SEGMET)
                .addPathSegment(LIST_SEGMET)
                .addQueryParameter(SIZE, size.toString())
                .addQueryParameter("q", filter)
                .build()

        client.newCall(buildRequest(url)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                consumer.onFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Klaxon().apply {
                    val json = this.parseJsonObject(StringReader(response.body?.string()))
                    json.array<Any>("results")?.let {
                        consumer.readRescipesResponse(
                                this.parseFromJsonArray<Recipe>(it) ?: emptyList()
                        )
                    }
                }
            }
        })
    }
}

interface TastyRecipiesApiConsumer {
    fun onFailure(message: String)
    fun readRescipesResponse(recipes: List<Recipe>)
}



