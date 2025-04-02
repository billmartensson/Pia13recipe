package se.magictechnology.pia13recipe

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel : ViewModel() {


    private val _recipies = MutableStateFlow(listOf<Recipe>())
    val recipies: StateFlow<List<Recipe>> = _recipies.asStateFlow()

    fun loadrecipes(ispreview : Boolean) {

        val database = Firebase.database.reference

        if(ispreview) {
            _recipies.value = Recipe("").examplerecipelist()
        } else {
            // Hämta från firebase

            database.child("androidrecipe").get().addOnSuccessListener { recipessnapshot ->

                var loadedrecipes = mutableListOf<Recipe>()

                recipessnapshot.children.forEach { childsnapshot ->
                    /*
                    val todo = childsnapshot.getValue<Todo>()
                    if(todo != null) {
                        todo.fbid = childsnapshot.key
                        loadedtodo.add(todo)
                    }
                    */

                    var loadrec = Recipe()
                    childsnapshot.getValue<Recipe>()?.let { rec ->
                        //todo.fbid = childsnapshot.key
                        loadrec = rec
                    }

                    var ingredientlist = mutableListOf<RecipeIngredient>()
                    childsnapshot.child("ingredients").children.forEach { ingredientsnap ->
                        ingredientsnap.getValue<RecipeIngredient>()?.let { ing ->
                            ingredientlist.add(ing)
                        }
                    }
                    loadrec.recipeingredients = ingredientlist

                    var stepslist = mutableListOf<RecipeStep>()
                    childsnapshot.child("steps").children.forEach { stepssnap ->
                        stepssnap.getValue<String>()?.let { txt ->
                            stepslist.add(RecipeStep(steptext = txt))
                        }
                    }
                    loadrec.recipesteps = stepslist

                    loadedrecipes.add(loadrec)
                }

                _recipies.value = loadedrecipes

            }
        }

    }

}