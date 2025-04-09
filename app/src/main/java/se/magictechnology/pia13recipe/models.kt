package se.magictechnology.pia13recipe

import com.google.firebase.database.Exclude
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Recipe(val title : String? = null) {
    var fbid : String? = null
    var isFav : Boolean? = null
    var description : String? = null

    var portions : Int? = null
    var portionname : String? = null

    var recipeingredients : List<RecipeIngredient>? = null
    var recipesteps : List<RecipeStep>? = null


    fun examplerecipe() : Recipe{

        var recipe = Recipe("Apelsinkaka")
        recipe.description = "En god apelsinkaka"
        recipe.portions = 4
        recipe.portionname = "kaka"

        var templist = mutableListOf<RecipeIngredient>()
        val r1 = RecipeIngredient("Apelsin", 100, "gram")
        val r2 = RecipeIngredient("Smör", 5, "tsk")
        val r3 = RecipeIngredient("Mjölk", 7, "dl")

        templist.add(r1)
        templist.add(r2)
        templist.add(r3)


        recipe.recipeingredients = templist




        var tempsteps = mutableListOf<RecipeStep>()

        tempsteps.add(RecipeStep("Elda apelsin"))
        tempsteps.add(RecipeStep("Smält smöret"))
        tempsteps.add(RecipeStep("Koka vatten"))

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


enum class IngredientUnit(val unitname : String) {
    DL("dl"),
    GRAM("gram")

}


@Serializable
data class RecipeIngredient(val name : String? = null, val amount : Int? = null, val unit : String? = null) {

    var isImperialUnit = false

    fun amounttext(baseportions : Int, portions : Int) : String {
        if(amount == null) {
            return ""
        }

        var amountresult = amount * (portions.toDouble()/baseportions.toDouble())

        if(isImperialUnit) {
            when(unit) {
                IngredientUnit.DL.unitname -> {
                    amountresult = amountresult*0.42
                }
                IngredientUnit.GRAM.unitname -> {
                    amountresult = amountresult * 0.035
                }
            }
        }

        //return amountresult.toString()
        return String.format("%.2f", amountresult)
    }

    fun unittext()  : String{

        if(isImperialUnit) {
            when(unit) {
                IngredientUnit.DL.unitname -> {
                    return "cups"
                }
                IngredientUnit.GRAM.unitname -> {
                    return "ounces"
                }
            }

            return unit!!
        }

        return unit!!
    }

}

@Serializable
data class RecipeStep(val steptext : String? = null)
