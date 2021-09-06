package ch.teko.rezept_app.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RapidApiTastyTest {
    @Test
    fun getCall(){
        val filteredRecipes = TastyRecipiesAdapter().getFilteredRecipes()
        assertThat(filteredRecipes).isNotEmpty()
    }

}