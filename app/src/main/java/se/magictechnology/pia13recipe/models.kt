package se.magictechnology.pia13recipe

import com.google.firebase.database.Exclude
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Recipe(val title : String? = null) {
    var description : String? = null


    var recipeingredients : List<RecipeIngredient>? = null
    var recipesteps : List<RecipeStep>? = null

    fun examplerecipe() : Recipe{

        var recipe = Recipe("Apelsinpaj")
        recipe.description = "En god apelsinpaj"

        var templist = mutableListOf<RecipeIngredient>()
        val r1 = RecipeIngredient("Apelsin", 100, "gram")
        templist.add(r1)
        val r2 = RecipeIngredient("Smör", 5, "tsk")
        templist.add(r2)
        recipe.recipeingredients = templist

        var tempsteps = mutableListOf<RecipeStep>()
        tempsteps.add(RecipeStep("Elda apelsin"))
        tempsteps.add(RecipeStep("Smält smöret"))

        recipe.recipesteps = tempsteps

        return recipe

    }


    fun examplerecipelist() : List<Recipe>{
        var templist = mutableListOf<Recipe>()
        val r1 = Recipe("Apelsinpaj")
        templist.add(r1)

        val r2 = Recipe("Bananpaj")
        templist.add(r2)

        val r3 = Recipe("Citronpaj")
        templist.add(r3)

        return templist
    }
}

@Serializable
data class RecipeIngredient(val name : String? = null, val amount : Int? = null, val unit : String? = null)

@Serializable
data class RecipeStep(val steptext : String? = null)
