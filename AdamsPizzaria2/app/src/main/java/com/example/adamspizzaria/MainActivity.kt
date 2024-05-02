package com.example.adamspizzaria

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {

    private lateinit var showPopupInstruct : ImageButton
    private lateinit var ingredients : TextView
    private val selectedIngredient = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        ingredients = findViewById(R.id.selectedPizza)

        showPopupInstruct = findViewById(R.id.pizzaRules)
        showPopupInstruct.setOnClickListener{
            showPopup(this)
        }
    }

    @SuppressLint("InflateParams")
    private fun showPopup(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup, null)

        val width = 1000
        val height = 1800

        val instructWindow = PopupWindow(popupView, width, height, true)
        instructWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 40, 100)

        val closeButton = popupView.findViewById<Button>(R.id.closeBtn)
        closeButton.setOnClickListener {
            instructWindow.dismiss()
        }
    }

    fun onIngredientButtonClick(view: View) {
        val getPizza = view as ImageButton

        // Reset background color of all image buttons
        val ingredientButtons = listOf(
            R.id.thin, R.id.pan, R.id.stuffed,
            R.id.hotSauce, R.id.ranchSauce, R.id.bbqSauce,
            R.id.pepe, R.id.chicken, R.id.bacon
        )

        ingredientButtons.forEach { buttonId ->
            val button = findViewById<ImageButton>(buttonId)
            if (button != getPizza) {
                button?.apply {
                    setBackgroundColor(Color.TRANSPARENT)
                    alpha = 1.0f // Reset alpha to fully opaque for non-selected buttons
                }
            }
        }

        // Set background color and adjust transparency of the clicked image button to indicate selection
        getPizza.setBackgroundColor(Color.TRANSPARENT) // Remove any background color
        getPizza.alpha = 0.5f // Set alpha to change transparency level

        val ingredient = when (getPizza.id) {
            R.id.thin -> "Thin"
            R.id.pan -> "Pan"
            R.id.stuffed -> "Stuffed"
            R.id.hotSauce -> "Spicy Sauce"
            R.id.ranchSauce -> "Ranch Sauce"
            R.id.bbqSauce -> "BBQ Sauce"
            R.id.pepe -> "Pepperoni"
            R.id.chicken -> "Chicken"
            R.id.bacon -> "Bacon"
            else -> ""
        }

        if (!selectedIngredient.contains(ingredient)) {
            selectedIngredient.add(ingredient)
        }

        ingredients.text = selectedIngredient.joinToString(", ")
    }



    @SuppressLint("SetTextI18n")
    fun onGetPizzaButtonClick(view: View) {
        Log.d("MainActivity", "onGetPizzaButtonClick called")

        // Reset background color and transparency of all image buttons
        val ingreButtons = listOf(R.id.thin, R.id.pan, R.id.stuffed,R.id.hotSauce, R.id.ranchSauce, R.id.bbqSauce,R.id.pepe, R.id.chicken, R.id.bacon)
        ingreButtons.forEach { findViewById<ImageButton>(it).apply {
            setBackgroundColor(Color.TRANSPARENT)
            alpha = if (selectedIngredient.contains(tag)) 0.5f else 1.0f
        } }

        // Check if exactly three specific ingredients are selected
        if (selectedIngredient.size != 3) {
            ingredients.text = "Please select one of each ingredients!"

            // Clear the selected ingredients
            selectedIngredient.clear()

            // Hide the ImageView
            val imageView = findViewById<ImageView>(R.id.sample)
            imageView.visibility = View.GONE
            return
        }

        // Prepare the message based on the comparison
        val message: String
        val imageResource: Int? // To store image resource ID

        when {
            // Thin Crust combinations
            //Pepperoni
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Thin Crust Spicy Pepperoni!"
                imageResource = R.drawable.thin_crust_spicy_pepperoni
            }
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Thin Crust Pepperoni Ranch!"
                imageResource = R.drawable.thin_crust_ranch_pepperoni
            }
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Thin Crust BBQ Pepperoni!"
                imageResource = R.drawable.thin_crust_bbq_pepperoni
            }
            //Chicken
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Thin Crust Spicy Chicken!"
                imageResource = R.drawable.thin_crust_spicy_chicken
            }
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Thin Crust Chicken Ranch!"
                imageResource = R.drawable.thin_crust_chicken_ranch
            }
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Thin Crust Chicken BBQ!"
                imageResource = R.drawable.thin_crust_chicken_bbq
            }
            //Bacon
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Thin Crust Spicy Bacon!"
                imageResource = R.drawable.thin_crust_spicy_bacon
            }
            (selectedIngredient.contains("Thin") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Thin Crust Bacon Ranch!"
                imageResource = R.drawable.thin_crust_bacon_ranch
            }

            (selectedIngredient.contains("Thin") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Thin Crust Bacon BBQ!"
                imageResource = R.drawable.thin_crust_bacon_bbq
            }


            // Pan Crust combinations
            //Pepperoni
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Pan Crust Spicy Pepperoni!"
                imageResource = R.drawable.pan_crust_spicy_pepperoni
            }
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Pan Crust Pepperoni Ranch!"
                imageResource = R.drawable.pan_crust_ranch_pepperoni
            }
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Pan Crust Pepperoni BBQ!"
                imageResource = R.drawable.pan_crust_bbq_pepperoni
            }
            //Chicken
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Pan Crust Spicy Chicken!"
                imageResource = R.drawable.pan_crust_spicy_chicken
            }
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Pan Crust Chicken Ranch!"
                imageResource = R.drawable.pan_crust_chicken_ranch
            }
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Pan Crust Chicken BBQ!"
                imageResource = R.drawable.pan_crust_chicken_bbq
            }
            //Bacon
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Pan Crust Spicy Bacon!"
                imageResource = R.drawable.pan_crust_spicy_bacon
            }
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Pan Crust Bacon Ranch!"
                imageResource = R.drawable.pan_crust_bacon_ranch
            }
            (selectedIngredient.contains("Pan") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Pan Crust Bacon BBQ!"
                imageResource = R.drawable.pan_crust_bacon_bbq
            }


            // Stuffed Crust combinations
            //Pepperoni
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Stuffed Crust Spicy Pepperoni!"
                imageResource = R.drawable.stuffed_crust_spicy_pepperoni
            }
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Stuffed Crust Ranch Pepperoni!"
                imageResource = R.drawable.stuffed_crust_ranch_pepperoni
            }
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Pepperoni")) -> {
                message = "Stuffed Crust BBQ Pepperoni!"
                imageResource = R.drawable.stuffed_crust_bbq_pepperoni
            }
            //Chicken
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Stuffed Crust Spicy Chicken!"
                imageResource = R.drawable.stuffed_crust_spicy_chicken
            }
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Stuffed Crust Chicken Ranch!"
                imageResource = R.drawable.stuffed_crust_chicken_ranch
            }
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Chicken")) -> {
                message = "Stuffed Crust Chicken BBQ!"
                imageResource = R.drawable.stuffed_crust_chicken_bbq
            }
            //Bacon
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("Spicy Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Stuffed Crust Spicy Bacon!"
                imageResource = R.drawable.stuffed_crust_spicy_bacon
            }
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("Ranch Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Stuffed Crust Bacon Ranch!"
                imageResource = R.drawable.stuffed_crust_bacon_ranch
            }
            (selectedIngredient.contains("Stuffed") && selectedIngredient.contains("BBQ Sauce") && selectedIngredient.contains("Bacon")) -> {
                message = "Stuffed Crust Bacon BBQ!"
                imageResource = R.drawable.stuffed_crust_bacon_bbq
            }
            else -> {
                message = "Please select one of each ingredient."
                imageResource = null // No specific image for this case
            }
        }


        // Set the message to the textview
        ingredients.text = message

        // Set the image if there's a corresponding image resource
        if (imageResource != null) {
            // Find the ImageView in your layout
            val imageView = findViewById<ImageView>(R.id.sample)
            // Set the image resource
            imageView.setImageResource(imageResource)
            // Make the image view visible
            imageView.visibility = View.VISIBLE
        } else {
            // If there's no corresponding image resource, hide the image view
            val imageView = findViewById<ImageView>(R.id.sample)
            imageView.visibility = View.GONE
        }
    }

    fun onRemakeButton(view: View) {
        // Clear selected ingredients and text in TextView
        selectedIngredient.clear()
        ingredients.text = ""

        // Reset background color and transparency of all image buttons
        val ingredientButtonIds = listOf(R.id.thin, R.id.pan, R.id.stuffed,R.id.hotSauce, R.id.ranchSauce, R.id.bbqSauce,R.id.pepe, R.id.chicken, R.id.bacon)
        ingredientButtonIds.forEach { buttonId ->
            val button = findViewById<ImageButton>(buttonId)
            button?.apply {
                setBackgroundColor(Color.TRANSPARENT)
                alpha = 1.0f // Reset alpha to fully opaque
            }
        }

        // Hide the ImageView if it's not null
        val imageView = findViewById<ImageView>(R.id.sample)
        imageView?.visibility = View.INVISIBLE
    }

}
