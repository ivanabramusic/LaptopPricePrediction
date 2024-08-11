package com.example.laptopprice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laptopprice.ui.theme.LaptopPriceTheme
import com.example.laptopprice.view.FirstScreen
import com.example.laptopprice.view.ResultScreen
import com.example.laptopprice.viewmodel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "firstscreen")
            {
                composable("firstscreen")
                {
                    val viewModel: MyViewModel = viewModel()
                    FirstScreen(navController, viewModel)
                }
                composable("result_screen/{price}") { backStackEntry ->
                    val price = backStackEntry.arguments?.getString("price") ?: "Unknown"
                    ResultScreen(navController, price)
                }

            }


        }

    }

}


