package se.magictechnology.pia13recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeDetailScreen(recipeviewmodel : RecipeViewModel, currentrecipe : Recipe) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("RECEPT")

        Text(currentrecipe.title!!)

        Text(currentrecipe.description!!)


        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(currentrecipe.recipeingredients!!) { ingredient ->
                Row {
                    Text(ingredient.amount!!.toString())
                    Text(ingredient.unit!!)
                    Text(ingredient.name!!)
                }
            }
        }

        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(currentrecipe.recipesteps!!) { step ->
                Row {
                    Text(step.steptext!!)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(RecipeViewModel(), Recipe().examplerecipe())
}