package se.magictechnology.pia13recipe

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collect

@Composable
fun RecipeListScreen(recipeviewmodel : RecipeViewModel, ispreview : Boolean = false, goRecipe : (recipe : Recipe) -> Unit) {

    val recipes by recipeviewmodel.recipies.collectAsState()

    val thenumber by recipeviewmodel.exampleCounterFlow.collectAsState(initial = 0)
    val useremail by recipeviewmodel.useremailFlow.collectAsState(initial = "")

    LaunchedEffect(true) {
        recipeviewmodel.loadrecipes(ispreview)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("LIST")
        if (LocalInspectionMode.current) {
            Text("DETTA ÄR PREVIEW")
        }
        LazyColumn {
            items(recipes) { recipe ->
                Row(modifier = Modifier.height(60.dp).clickable {
                    goRecipe(recipe)
                }) {
                    Text(recipe.title!!)

                    if(recipe.isFav!!) {
                        Button(onClick = {
                            recipeviewmodel.switchFav(recipe)
                        }) {
                            Text("Remove fav")
                        }
                    } else {
                        Button(onClick = {
                            recipeviewmodel.switchFav(recipe)
                        }) {
                            Text("Make fav")
                        }
                    }
                }
            }
        }

        /*
        Text("$thenumber", fontSize = 40.sp)

        Button(onClick = {
            recipeviewmodel.savenumber()
        }) {
            Text("PLUS")
        }

        Text(useremail, fontSize = 40.sp)

        Button(onClick = {
            recipeviewmodel.saveemail("banan@fruit.com")
        }) {
            Text("SET EMAIL")
        }
        */

    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview() {

    RecipeListScreen(recipeviewmodel = viewModel(), ispreview = true, goRecipe = {})
}