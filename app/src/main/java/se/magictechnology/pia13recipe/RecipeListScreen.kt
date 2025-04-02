package se.magictechnology.pia13recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun RecipeListScreen(recipeviewmodel : RecipeViewModel, ispreview : Boolean = false, goRecipe : (recipe : Recipe) -> Unit) {

    val recipes by recipeviewmodel.recipies.collectAsState()

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
                Row(modifier = Modifier.clickable {
                    goRecipe(recipe)
                }) {
                    Text(recipe.title!!)

                    Text("Steps: ${recipe.recipesteps?.size}" )

                    Text("Ingredients: ${recipe.recipeingredients?.size}" )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview() {
    RecipeListScreen(recipeviewmodel = viewModel(), ispreview = true, goRecipe = {})
}