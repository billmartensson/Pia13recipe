package se.magictechnology.pia13recipe

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel : ViewModel() {

    private val _recipies = MutableStateFlow(listOf<Recipe>())
    val recipies: StateFlow<List<Recipe>> = _recipies.asStateFlow()

    fun loadrecipes(ispreview : Boolean) {

        if(ispreview) {
            _recipies.value = Recipe("").examplerecipelist()
        } else {
            // Hämta från firebase
        }

    }

}