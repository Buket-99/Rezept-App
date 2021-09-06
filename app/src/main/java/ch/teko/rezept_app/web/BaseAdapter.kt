package ch.teko.rezept_app.web

import okhttp3.HttpUrl
import okhttp3.Request

const val SHEMA = "https"
const val HOST = "tasty.p.rapidapi.com"
const val RECIPIES_SEGMET = "recipes"
const val LIST_SEGMET = "list"
const val DETAIL_SEGMENT = "detail"

const val SIZE = "size"
const val ID = "id"

private const val RAPI_API_KEY = "b4246eb42emshe352b91c8cfec9ap1e6175jsn44b9cffb5e50"

abstract class BaseAdapter {

    fun buildRequest(url: HttpUrl) = Request.Builder()
            .url(url)
            .header("x-rapidapi-key", RAPI_API_KEY)
            .header("useQueryString", "true")
            .header("x-rapidapi-host", HOST)
            .build()
}