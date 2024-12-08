package com.example.adminfastfood

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminfastfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addFoodItem.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddItemActivity::class.java))
        }

        binding.addFoodItemPopular.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddPopularItemActivity::class.java))
        }

        binding.addMenuItem.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddMenuItemActivity::class.java))
        }

        binding.addMenuItem2.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddMenuItemActivity::class.java))
        }

        binding.editItems.setOnClickListener {
            startActivity(Intent(this@MainActivity,EditItemActivity::class.java))
        }



    }
}