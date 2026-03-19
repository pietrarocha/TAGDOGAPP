package com.example.apptagdog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun RastreamentoScreen(navController: NavController) {
    // Cores do seu design corrigidas
    val textDark = Color(0xFF1B243BL)
    val textGray = Color(0xFF888888L)
    val liveRedText = Color(0xFFE53935L)
    val liveRedBg = Color(0xFFFFEBEEL)
    val safeGreenText = Color(0xFF2E7D32L)
    val safeGreenBg = Color(0xFFE8F5E9L)
    val safeGreenBorder = Color(0xFF4CAF50L)

    // Coordenadas simuladas (Ex: São Paulo)
    val centerLocation = LatLng(-23.5505, -46.6333)
    val bobLocation = LatLng(-23.5510, -46.6338) // Um pouco deslocado do centro

    // Controle da câmera do mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(centerLocation, 16f)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // -----------------------------------------------------------------
        // 1. O MAPA REAL (Fica no fundo da Box)
        // -----------------------------------------------------------------
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false, // Esconde os botões nativos de zoom para ficar limpo
                myLocationButtonEnabled = false
            )
        ) {
            // Desenha o Perímetro Seguro no mapa real
            Circle(
                center = centerLocation,
                radius = 300.0, // Raio em metros
                fillColor = safeGreenBg.copy(alpha = 0.4f),
                strokeColor = safeGreenBorder,
                strokeWidth = 5f
            )

            // Marcador do Bob
            MarkerComposable(
                state = MarkerState(position = bobLocation),
                title = "Bob está aqui!"
            ) {
                // Pin customizado idêntico ao do seu design
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White) // Borda branca
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(safeGreenBorder), // Fundo verde para a foto
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Pets,
                        contentDescription = "Bob",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }

        // -----------------------------------------------------------------
        // 2. ELEMENTOS DO TOPO (Flutuando sobre o Mapa)
        // -----------------------------------------------------------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Monitoramento ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )
                Box(
                    modifier = Modifier
                        .background(liveRedBg, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "AO VIVO",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = liveRedText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = safeGreenBg,
                shadowElevation = 4.dp,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = "Seguro",
                        tint = safeGreenBorder,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dentro do Perímetro Seguro",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = safeGreenText
                    )
                }
            }
        }

        // -----------------------------------------------------------------
        // 3. CARTÃO INFERIOR (Flutuando na base)
        // -----------------------------------------------------------------
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE0E0E0)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.labrador),
                        contentDescription = "Foto do Bob",
                        modifier = Modifier.fillMaxSize(), // Preenche todo o quadrado
                        contentScale = ContentScale.Crop   // Recorta sem distorcer
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Bob",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )
                    Text(
                        text = "Labrador",
                        fontSize = 14.sp,
                        color = textGray
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "TAG-9824-XP",
                        fontSize = 12.sp,
                        color = textGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRastreamentoScreen() {
    MaterialTheme {
        RastreamentoScreen(navController = androidx.navigation.compose.rememberNavController())
    }
}