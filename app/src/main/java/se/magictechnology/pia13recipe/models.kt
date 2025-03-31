package se.magictechnology.pia13recipe

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(val title : String? = null) {
    var description : String? = null

    var ingredients : List<RecipeIngredient>? = null
    var steps : List<RecipeSteps>? = null

    fun examplerecipe() : Recipe{

        var recipe = Recipe("Apelsinpaj")
        recipe.description = "En god apelsinpaj"

        var templist = mutableListOf<RecipeIngredient>()
        val r1 = RecipeIngredient("Apelsin", 100, "gram")
        templist.add(r1)
        val r2 = RecipeIngredient("Smör", 5, "tsk")
        templist.add(r2)
        recipe.ingredients = templist

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
data class RecipeSteps(val steptext : String? = null)
