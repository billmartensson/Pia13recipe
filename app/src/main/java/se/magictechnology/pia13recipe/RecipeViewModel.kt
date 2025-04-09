package se.magictechnology.pia13recipe

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RecipeViewModel(val datastore : DataStore<Preferences>) : ViewModel() {

    private val _recipies = MutableStateFlow(listOf<Recipe>())
    val recipies: StateFlow<List<Recipe>> = _recipies.asStateFlow()

    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")

    val exampleCounterFlow: Flow<Int> = datastore.data
        .map { preferences ->
            // No type safety.
            preferences[EXAMPLE_COUNTER] ?: 0
        }


    val USEREMAIL = stringPreferencesKey("useremail")
    val useremailFlow: Flow<String> = datastore.data
        .map { preferences ->
            // No type safety.
            preferences[USEREMAIL] ?: ""
        }

    val RECIPEFAVS = stringSetPreferencesKey("recipefavs")
    val recipefavsFlow: Flow<Set<String>> = datastore.data
        .map { preferences ->
            // No type safety.
            preferences[RECIPEFAVS] ?: setOf()
        }


    fun savenumber() {
        viewModelScope.launch {
            datastore.edit { settings ->
                val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
                settings[EXAMPLE_COUNTER] = currentCounterValue + 1
            }
        }

    }

    fun saveemail(email : String) {
        viewModelScope.launch {
            datastore.edit { settings ->
                settings[USEREMAIL] = email
            }
        }

    }

    fun switchFav(rec : Recipe) {
        viewModelScope.launch {
            datastore.edit { settings ->
                val favs = settings[RECIPEFAVS] ?: setOf()
                var mutfavs = favs.toMutableSet()
                if(favs.contains(rec.fbid!!)) {
                    mutfavs.remove(rec.fbid!!)
                } else {
                    mutfavs.add(rec.fbid!!)
                }
                settings[RECIPEFAVS] = mutfavs
            }
            loadrecipes(false)
        }
    }

    fun updatefav() {

    }

    fun loadrecipes(ispreview : Boolean) {

        _recipies.value = listOf<Recipe>()

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
                    loadrec.fbid = childsnapshot.key

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

                viewModelScope.launch {
                    recipefavsFlow.collect { favs ->

                        loadedrecipes.forEachIndexed { index, rec ->
                            loadedrecipes[index].isFav = favs.contains(rec.fbid)
                        }

                        _recipies.value = loadedrecipes
                    }
                }



            }
        }

    }

}