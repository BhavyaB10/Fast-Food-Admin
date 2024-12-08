package com.example.adminfastfood

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminfastfood.databinding.ActivityAddMenuItemBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMenuItemActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddMenuItemBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddMenuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference.child("MenuItems")

        binding.buttonAddMenuItem.setOnClickListener {
            val name = binding.editTextFoodName.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            val price = binding.editTextFoodPrice.text.toString().trim()

            // Validate inputs
            if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Save to database
            saveFoodToDatabase(name, description, price)

        }
    }
    private fun saveFoodToDatabase(name: String, description: String, price: String) {

        // Generate a unique ID for each food item
        val foodId = database.push().key

        // Create a food item object
        val foodItem = Food(name, description, price)

        // Save to Firebase Realtime Database
        if (foodId != null) {
            database.child(foodId).setValue(foodItem)
                .addOnSuccessListener {
                    Toast.makeText(this, "Food item added successfully!", Toast.LENGTH_SHORT).show()

                    // Clear the input fields
                    binding.editTextFoodName.text.clear()
                    binding.editTextDescription.text.clear()
                    binding.editTextFoodPrice.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Failed to add food item: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

}