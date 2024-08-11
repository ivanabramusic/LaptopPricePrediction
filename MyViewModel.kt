package com.example.laptopprice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class MyViewModel(application: Application):AndroidViewModel(application) {
    suspend fun predictLaptopPrice(
        inches: Double,
        ram: Int,
        weight: Double,
        cpu: String,
        gpu: String,
        screenResolution: String,
        memory: String
    ): String? {
        val client = OkHttpClient()

        // Create the JSON request body using the provided attributes
        val requestBody = """{
        "Inputs": {
            "input1": [
                {
                    "Inches": $inches,
                    "Ram": $ram,
                    "Weight": $weight,
                    "Cpu_$cpu": 1,
                    "Gpu_$gpu": 1,
                    "ScreenResolution_$screenResolution": 1,
                    "Memory_$memory": 1
                }
            ]
        },
        "GlobalParameters": {}
    }""".trimIndent()

        // Replace with your API key
        val apiKey = "9uZxugz8AoWBOZLNfL0qrQ5elAHoCRsu"
        if (apiKey.isEmpty()) {
            throw Exception("A key should be provided to invoke the endpoint")
        }

        val request = Request.Builder()
            .url("http://14bc6f61-a846-43e0-83d2-d4a8712e92a5.westeurope.azurecontainer.io/score")
            .post(RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), requestBody))
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        // Make the request and return the result
        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body?.string() // Return the result as a string
                } else {
                    throw Exception("Request failed with status code: ${response.code}\nResponse: ${response.body?.string()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null // Return null if there's an error
            }
        }
    }


}