package se.magictechnology.pia13recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipelist") {
        composable("recipelist") {
            RecipeListScreen()
        }
        composable("recipedetail") {
            RecipeDetailScreen()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RecipeNavPreview() {
    RecipeNav()
}