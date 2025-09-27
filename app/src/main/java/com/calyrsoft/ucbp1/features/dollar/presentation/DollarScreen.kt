package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> {
                Text(
                    text = "❌ Error: ${stateValue.message}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            DollarViewModel.DollarUIState.Loading -> {
                CircularProgressIndicator()
                Spacer(Modifier.height(12.dp))
                Text("Cargando cotización...")
            }
            is DollarViewModel.DollarUIState.Success -> {
                DollarCard(label = "Dólar Oficial", value = stateValue.data.dollarOfficial ?: "-", color = Color(0xFF1E88E5))
                Spacer(Modifier.height(12.dp))
                DollarCard(label = "Dólar Paralelo", value = stateValue.data.dollarParallel ?: "-", color = Color(0xFFD32F2F))
                Spacer(Modifier.height(12.dp))
                DollarCard(label = "Compra", value = stateValue.data.dollarCompra ?: "-", color = Color(0xFF388E3C))
                Spacer(Modifier.height(12.dp))
                DollarCard(label = "Venta", value = stateValue.data.dollarVenta ?: "-", color = Color(0xFFF57C00))
            }
        }
    }
}

@Composable
fun DollarCard(label: String, value: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
    }
}
