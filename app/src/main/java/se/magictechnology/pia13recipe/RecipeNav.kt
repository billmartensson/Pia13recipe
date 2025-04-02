package se.magictechnology.pia13recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun RecipeNav(recipeviewmodel : RecipeViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipelist") {
        composable("recipelist") {
            RecipeListScreen(recipeviewmodel, ispreview = false, goRecipe = { recipe ->
                navController.navigate(recipe)
            })
        }

        /*
        composable<Recipe> { backStackEntry ->
            val navrecipe : Recipe = backStackEntry.toRoute()

            RecipeDetailScreen(recipeviewmodel, navrecipe)
        }
        */
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeNavPreview() {
    RecipeNav()
}