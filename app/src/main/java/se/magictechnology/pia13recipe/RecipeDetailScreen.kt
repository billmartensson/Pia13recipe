package se.magictechnology.pia13recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeDetailScreen(recipeviewmodel : RecipeViewModel, currentrecipe : Recipe) {

    var portions by remember { mutableStateOf(currentrecipe.portions ?: 1) }
    
    
    Column(modifier = Modifier.fillMaxSize()) {


        LazyColumn(modifier = Modifier) {

            item {
                Image(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    painter = painterResource(R.drawable.frog),
                    contentDescription = "frog",
                    contentScale = ContentScale.Crop
                )


                Text(
                    currentrecipe.title ?: "",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 25.sp
                )

                Text(
                    currentrecipe.description ?: "",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontSize = 16.sp
                )

            }

            item {

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 10.dp)) {
                    Text("Portions")

                    Text("-",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable {
                                if(portions > 1) {
                                    portions -= 1
                                }
                            },
                        fontSize = 35.sp,

                    )

                    Text("$portions")

                    Text("+",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable {
                                portions += 1
                            },
                        fontSize = 35.sp,

                        )

                    Text(currentrecipe.portionname ?: "")
                }

                Text(
                    "Ingredients",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 25.sp
                )
            }

            currentrecipe.recipeingredients?.let { recipeingredients ->
                items(recipeingredients) { ingredient ->
                    Row(modifier = Modifier.padding(10.dp)) {

                        Text(
                            ingredient.amounttext(currentrecipe.portions ?: 1,  portions),
                            modifier = Modifier.width(35.dp)
                        )

                        Text(ingredient.unittext(),
                            modifier = Modifier.padding(start = 5.dp).width(45.dp)
                        )

                        Text(ingredient.name ?: "",
                            modifier = Modifier.padding(start = 5.dp)
                        )

                    }
                }
            }

            item {
                Text(
                    "Instructions",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 25.sp
                )
            }

            currentrecipe.recipesteps?.let { recipesteps ->
                itemsIndexed(recipesteps) { index, step ->
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                        Text((index + 1).toString())
                        Text(
                            step.steptext ?: "",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
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