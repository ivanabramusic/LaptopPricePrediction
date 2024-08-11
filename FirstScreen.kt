package com.example.laptopprice.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.laptopprice.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(navController: NavController, viewModel: MyViewModel) {
    var inches by remember { mutableStateOf("") }
    var ram by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var selectedCpu by remember { mutableStateOf("") }
    var selectedGpu by remember { mutableStateOf("") }
    var selectedResolution by remember { mutableStateOf("") }
    var selectedMemory by remember { mutableStateOf("") }

    val cpuOptions = listOf("AMD A10-Series 9600P 2.4GHz",
        "AMD A10-Series 9620P 2.5GHz",
        "AMD A10-Series A10-9620P 2.5GHz",
        "AMD A12-Series 9700P 2.5GHz",
        "AMD A12-Series 9720P 2.7GHz",
        "AMD A12-Series 9720P 3.6GHz",
        "AMD A4-Series 7210 2.2GHz",
        "AMD A6-Series 7310 2GHz",
        "AMD A6-Series 9220 2.5GHz",
        "AMD A6-Series 9220 2.9GHz",
        "AMD A6-Series A6-9220 2.5GHz",
        "AMD A8-Series 7410 2.2GHz",
        "AMD A9-Series 9410 2.9GHz",
        "AMD A9-Series 9420 2.9GHz",
        "AMD A9-Series 9420 3GHz",
        "AMD A9-Series A9-9420 3GHz",
        "AMD E-Series 6110 1.5GHz",
        "AMD E-Series 7110 1.8GHz",
        "AMD E-Series 9000 2.2GHz",
        "AMD E-Series 9000e 1.5GHz",
        "AMD E-Series E2-6110 1.5GHz",
        "AMD E-Series E2-9000 2.2GHz",
        "AMD E-Series E2-9000e 1.5GHz",
        "AMD FX 8800P 2.1GHz",
        "AMD FX 9830P 3GHz",
        "AMD Ryzen 1600 3.2GHz",
        "AMD Ryzen 1700 3GHz",
        "Intel Atom X5-Z8350 1.44GHz",
        "Intel Atom Z8350 1.92GHz",
        "Intel Atom x5-Z8300 1.44GHz",
        "Intel Atom x5-Z8350 1.44GHz",
        "Intel Atom x5-Z8550 1.44GHz",
        "Intel Celeron Dual Core 3205U 1.5GHz",
        "Intel Celeron Dual Core 3855U 1.6GHz",
        "Intel Celeron Dual Core N3050 1.6GHz",
        "Intel Celeron Dual Core N3060 1.60GHz",
        "Intel Celeron Dual Core N3060 1.6GHz",
        "Intel Celeron Dual Core N3350 1.1GHz",
        "Intel Celeron Dual Core N3350 2.0GHz",
        "Intel Celeron Dual Core N3350 2GHz",
        "Intel Celeron Quad Core N3160 1.6GHz",
        "Intel Celeron Quad Core N3450 1.1GHz",
        "Intel Celeron Quad Core N3710 1.6GHz",
        "Intel Core M 1.1GHz,Intel Core M 1.2GHz",
        "Intel Core M 6Y30 0.9GHz",
        "Intel Core M 6Y54 1.1GHz",
        "Intel Core M 6Y75 1.2GHz",
        "Intel Core M 7Y30 1.0GHz",
        "Intel Core M M3-6Y30 0.9GHz",
        "Intel Core M M7-6Y75 1.2GHz",
        "Intel Core M m3 1.2GHz",
        "Intel Core M m3-7Y30 2.2GHz",
        "Intel Core M m7-6Y75 1.2GHz",
        "Intel Core i3 6006U 2.0GHz",
        "Intel Core i3 6006U 2.2GHz",
        "Intel Core i3 6006U 2GHz",
        "Intel Core i3 6100U 2.1GHz",
        "Intel Core i3 6100U 2.3GHz",
        "Intel Core i3 7100U 2.4GHz",
        "Intel Core i3 7130U 2.7GHz",
        "Intel Core i5 1.3GHz",
        "Intel Core i5 1.6GHz",
        "Intel Core i5 1.8GHz",
        "Intel Core i5 2.0GHz",
        "Intel Core i5 2.3GHz",
        "Intel Core i5 2.9GHz",
        "Intel Core i5 3.1GHz",
        "Intel Core i5 6200U 2.3GHz",
        "Intel Core i5 6260U 1.8GHz",
        "Intel Core i5 6300HQ 2.3GHz",
        "Intel Core i5 6300U 2.4GHz",
        "Intel Core i5 6440HQ 2.6GHz",
        "Intel Core i5 7200U 2.50GHz",
        "Intel Core i5 7200U 2.5GHz",
        "Intel Core i5 7200U 2.70GHz",
        "Intel Core i5 7200U 2.7GHz",
        "Intel Core i5 7300HQ 2.5GHz",
        "Intel Core i5 7300U 2.6GHz",
        "Intel Core i5 7440HQ 2.8GHz",
        "Intel Core i5 7500U 2.7GHz",
        "Intel Core i5 7Y54 1.2GHz",
        "Intel Core i5 7Y57 1.2GHz",
        "Intel Core i5 8250U 1.6GHz",
        "Intel Core i7 2.2GHz",
        "Intel Core i7 2.7GHz",
        "Intel Core i7 2.8GHz",
        "Intel Core i7 2.9GHz",
        "Intel Core i7 6500U 2.50GHz",
        "Intel Core i7 6500U 2.5GHz",
        "Intel Core i7 6560U 2.2GHz",
        "Intel Core i7 6600U 2.6GHz",
        "Intel Core i7 6700HQ 2.6GHz",
        "Intel Core i7 6820HK 2.7GHz",
        "Intel Core i7 6820HQ 2.7GHz",
        "Intel Core i7 6920HQ 2.9GHz",
        "Intel Core i7 7500U 2.5GHz",
        "Intel Core i7 7500U 2.7GHz",
        "Intel Core i7 7560U 2.4GHz",
        "Intel Core i7 7600U 2.8GHz",
        "Intel Core i7 7660U 2.5GHz",
        "Intel Core i7 7700HQ 2.7GHz",
        "Intel Core i7 7700HQ 2.8GHz",
        "Intel Core i7 7820HK 2.9GHz",
        "Intel Core i7 7820HQ 2.9GHz",
        "Intel Core i7 7Y75 1.3GHz",
        "Intel Core i7 8550U 1.8GHz",
        "Intel Core i7 8650U 1.9GHz",
        "Intel Pentium Dual Core 4405U 2.1GHz",
        "Intel Pentium Dual Core 4405Y 1.5GHz",
        "Intel Pentium Dual Core N4200 1.1GHz",
        "Intel Pentium Quad Core N3700 1.6GHz",
        "Intel Pentium Quad Core N3710 1.6GHz",
        "Intel Pentium Quad Core N4200 1.1GHz",
        "Intel Xeon E3-1505M V6 3GHz",
        "Intel Xeon E3-1535M v5 2.9GHz",
        "Intel Xeon E3-1535M v6 3.1GHz",
        "Samsung Cortex A72&A53 2.0GHz")
    val gpuOptions = listOf("AMD FirePro W4190M",
        "AMD FirePro W4190M",
        "AMD FirePro W5130M",
        "AMD FirePro W6150M",
        "AMD R17M-M1-70",
        "AMD R4 Graphics",
        "AMD Radeon 520",
        "AMD Radeon 530",
        "AMD Radeon 540",
        "AMD Radeon Pro 455",
        "AMD Radeon Pro 555",
        "AMD Radeon Pro 560",
        "AMD Radeon R2",
        "AMD Radeon R2 Graphics",
        "AMD Radeon R3",
        "AMD Radeon R4",
        "AMD Radeon R4 Graphics",
        "AMD Radeon R5",
        "AMD Radeon R5 430",
        "AMD Radeon R5 520",
        "AMD Radeon R5 M315",
        "AMD Radeon R5 M330",
        "AMD Radeon R5 M420",
        "AMD Radeon R5 M420X",
        "AMD Radeon R5 M430",
        "AMD Radeon R7",
        "AMD Radeon R7 Graphics",
        "AMD Radeon R7 M360",
        "AMD Radeon R7 M365X",
        "AMD Radeon R7 M440",
        "AMD Radeon R7 M445",
        "AMD Radeon R7 M460",
        "AMD Radeon R7 M465",
        "AMD Radeon R9 M385",
        "AMD Radeon RX 540",
        "AMD Radeon RX 550",
        "AMD Radeon RX 560",
        "AMD Radeon RX 580",
        "ARM Mali T860 MP4",
        "Intel Graphics 620",
        "Intel HD Graphics",
        "Intel HD Graphics 400",
        "Intel HD Graphics 405",
        "Intel HD Graphics 500",
        "Intel HD Graphics 505",
        "Intel HD Graphics 510",
        "Intel HD Graphics 515",
        "Intel HD Graphics 520",
        "Intel HD Graphics 530",
        "Intel HD Graphics 5300",
        "Intel HD Graphics 540",
        "Intel HD Graphics 6000",
        "Intel HD Graphics 615",
        "Intel HD Graphics 620",
        "Intel HD Graphics 620",
        "Intel HD Graphics 630",
        "Intel Iris Graphics 540",
        "Intel Iris Graphics 550","Intel Iris Plus Graphics 640","Intel Iris Plus Graphics 650","Intel Iris Pro Graphics",
        "Intel UHD Graphics 620","Nvidia GTX 980 SLI","Nvidia GeForce 150MX","Nvidia GeForce 920","Nvidia GeForce 920M",
        "Nvidia GeForce 920MX","Nvidia GeForce 920MX","Nvidia GeForce 930M","Nvidia GeForce 930MX","Nvidia GeForce 930MX",
        "Nvidia GeForce 940M","Nvidia GeForce 940MX","Nvidia GeForce 960M","Nvidia GeForce GT 940MX","Nvidia GeForce GTX 1050",
        "Nvidia GeForce GTX 1050 Ti","Nvidia GeForce GTX 1050M","Nvidia GeForce GTX 1050Ti","Nvidia GeForce GTX 1060",
        "Nvidia GeForce GTX 1070","Nvidia GeForce GTX 1070M","Nvidia GeForce GTX 1080","Nvidia GeForce GTX 930MX",
        "Nvidia GeForce GTX 940M","Nvidia GeForce GTX 940MX","Nvidia GeForce GTX 950M","Nvidia GeForce GTX 960",
        "Nvidia GeForce GTX 960<U+039C>","Nvidia GeForce GTX 960M","Nvidia GeForce GTX 965M","Nvidia GeForce GTX 970M",
        "Nvidia GeForce GTX 980","Nvidia GeForce GTX 980M","Nvidia GeForce GTX1050 Ti","Nvidia GeForce GTX1060","Nvidia GeForce GTX1080","Nvidia GeForce MX130",
        "Nvidia GeForce MX150","Nvidia Quadro 3000M","Nvidia Quadro M1000M","Nvidia Quadro M1200","Nvidia Quadro M2000M",
        "Nvidia Quadro M2200","Nvidia Quadro M2200M","Nvidia Quadro M3000M","Nvidia Quadro M500M","Nvidia Quadro M520M",
        "Nvidia Quadro M620","Nvidia Quadro M620M")
    val resolutionOptions = listOf(
        "1366x768",
        "1440x900",
        "1600x900",
        "1920x1080",
        "2560x1440",
        "4K Ultra HD / Touchscreen 3840x2160",
        "4K Ultra HD 3840x2160",
        "Full HD / Touchscreen 1920x1080",
        "Full HD 1920x1080",
        "IPS Panel 1366x768",
        "IPS Panel 2560x1440",
        "IPS Panel 4K Ultra HD / Touchscreen 3840x2160",
        "IPS Panel 4K Ultra HD 3840x2160",
        "IPS Panel Full HD / Touchscreen 1920x1080",
        "IPS Panel Full HD 1366x768",
        "IPS Panel Full HD 1920x1080",
        "IPS Panel Full HD 1920x1200",
        "IPS Panel Full HD 2160x1440",
        "IPS Panel Full HD 2560x1440",
        "IPS Panel Quad HD+ / Touchscreen 3200x1800",
        "IPS Panel Quad HD+ 2560x1440",
        "IPS Panel Quad HD+ 3200x1800",
        "IPS Panel Retina Display 2304x1440",
        "IPS Panel Retina Display 2560x1600",
        "IPS Panel Retina Display 2736x1824",
        "IPS Panel Retina Display 2880x1800",
        "IPS Panel Touchscreen / 4K Ultra HD 3840x2160",
        "IPS Panel Touchscreen 1366x768",
        "IPS Panel Touchscreen 1920x1200",
        "IPS Panel Touchscreen 2400x1600",
        "IPS Panel Touchscreen 2560x1440",
        "Quad HD+ / Touchscreen 3200x1800",
        "Quad HD+ 3200x1800",
        "Touchscreen / 4K Ultra HD 3840x2160",
        "Touchscreen / Full HD 1920x1080",
        "Touchscreen / Quad HD+ 3200x1800",
        "Touchscreen 1366x768",
        "Touchscreen 2256x1504",
        "Touchscreen 2400x1600",
        "Touchscreen 2560x1440"

    )
    val memoryOptions = listOf("1.0TB HDD","1.0TB Hybrid","128GB Flash Storage",
        "128GB HDD","128GB SSD","128GB SSD +  1TB HDD","128GB SSD +  2TB HDD",
        "16GB Flash Storage","16GB SSD","180GB SSD","1TB HDD","1TB HDD +  1TB HDD",
        "1TB SSD","1TB SSD +  1TB HDD","240GB SSD","256GB Flash Storage",
        "256GB SSD","256GB SSD +  1.0TB Hybrid","256GB SSD +  1TB HDD","256GB SSD +  256GB SSD",
        "256GB SSD +  2TB HDD","256GB SSD +  500GB HDD","2TB HDD","32GB Flash Storage","32GB HDD",
        "32GB SSD","500GB HDD","508GB Hybrid","512GB Flash Storage","512GB SSD","512GB SSD +  1.0TB Hybrid",
        "512GB SSD +  1TB HDD","512GB SSD +  256GB SSD","512GB SSD +  2TB HDD","512GB SSD +  512GB SSD",
        "64GB Flash Storage","64GB Flash Storage +  1TB HDD","64GB SSD","8GB SSD")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Title
        Text(
            text = "Get Your Laptop Price",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input fields for inches, RAM, and weight
        OutlinedTextField(
            value = inches,
            onValueChange = { inches = it },
            label = { Text("Screen Size (inches)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        OutlinedTextField(
            value = ram,
            onValueChange = { ram = it },
            label = { Text("RAM (GB)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        // Dropdown for CPU
        DropdownMenuWithLabel(
            label = "Select CPU",
            options = cpuOptions,
            selectedOption = selectedCpu,
            onOptionSelected = { selectedCpu = it }
        )

        // Dropdown for GPU
        DropdownMenuWithLabel(
            label = "Select GPU",
            options = gpuOptions,
            selectedOption = selectedGpu,
            onOptionSelected = { selectedGpu = it }
        )

        // Dropdown for Screen Resolution
        DropdownMenuWithLabel(
            label = "Select Resolution",
            options = resolutionOptions,
            selectedOption = selectedResolution,
            onOptionSelected = { selectedResolution = it }
        )

        // Dropdown for Memory
        DropdownMenuWithLabel(
            label = "Select Memory",
            options = memoryOptions,
            selectedOption = selectedMemory,
            onOptionSelected = { selectedMemory = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Predict Button
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    val predictedPrice = viewModel.predictLaptopPrice(
                        inches = inches.toDouble(),
                        ram = ram.toInt(),
                        weight = weight.toDouble(),
                        cpu = selectedCpu,
                        gpu = selectedGpu,
                        screenResolution = selectedResolution,
                        memory = selectedMemory
                    )

                    // Navigate to the ResultScreen with the predicted price
                    if (predictedPrice != null) {
                        navController.navigate("result_screen/${predictedPrice}")
                    } else {
                        navController.navigate("result_screen/${predictedPrice}")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Predict Price")
        }
    }
}

@Composable
fun DropdownMenuWithLabel(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(text = if (selectedOption.isEmpty()) label else selectedOption)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}
