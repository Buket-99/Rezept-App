package ch.teko.rezept_app.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL
import java.util.ArrayList

data class DetailRecipe(val id: Int, val name: String, val thumbnail_url: String, val instructions: List<Instruction>, val sections: List<Section>) {
    fun getBitmap(): Bitmap? {
        val url = URL(thumbnail_url)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }

    fun instructionAsText() = instructions.joinToString("\n") { it.display_text }

    fun allComponents(): List<Component> {
        val components = mutableListOf<Component>()
        sections.forEach {
            components.addAll(it.components)
        }
        return components
    }
}

data class Instruction(val display_text: String)

data class Section(val name: String, val components: List<Component>)

data class Component(val raw_text: String)