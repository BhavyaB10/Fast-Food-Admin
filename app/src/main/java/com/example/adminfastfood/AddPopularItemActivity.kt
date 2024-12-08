package com.example.adminfastfood

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminfastfood.databinding.ActivityAddPopularItemBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddPopularItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPopularItemBinding

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddPopularItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference.child("PopularFoodItems")

        binding.buttonAddPopularFoodItem.setOnClickListener {
            val name = binding.editTextFoodName.text.toString().trim()

            // Validate inputs
            if (name.isEmpty()) {
                Toast.makeText(this, "Name is required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Save to database
            else saveFoodToDatabase(name)
        }
    }
    private fun saveFoodToDatabase(name: String) {
        // Generate a unique ID for each food item
        val foodId = database.push().key

        // Create a food item object
        val foodItem = PopularFood(name)

        // Save to Firebase Realtime Database
        if (foodId != null) {
            database.child(foodId).setValue(foodItem)
                .addOnSuccessListener {
                    Toast.makeText(this, "Food item added successfully!", Toast.LENGTH_SHORT).show()
                    // Clear the input fields
                    binding.editTextFoodName.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to add food item: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}