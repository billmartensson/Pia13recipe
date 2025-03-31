package se.magictechnology.pia13recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeDetailScreen() {
    Column {
        Text("RECEPT")
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen()
}