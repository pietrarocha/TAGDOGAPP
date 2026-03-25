package com.example.apptagdog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EsqueceuSenhaScreen(navController: NavController) {
    val primaryOrange = Color(0xFFD37D41)
    val backgroundGray = Color(0xFFF5F6F8)
    val darkText = Color(0xFF1B243B)

    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
    ) {
        // 1. Imagem de Fundo
        Image(
            painter = painterResource(R.drawable.cachorros), // Troque pelo seu R.drawable.foto_cachorros
            contentDescription = "Fundo de cachorros",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )

        // 2. Conteúdo Principal (Card e Textos)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(220.dp))

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxWidth()
            ) {
                // O Card Branco
                Card(
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Recuperar Senha",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = darkText
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Digite seu e-mail para receber\nas instruções de recuperação.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Campo E-mail
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("E-mail", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryOrange,
                                unfocusedBorderColor = Color.LightGray,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Botão Enviar
                        Button(
                            onClick = { /* Aqui vai a lógica de enviar email no futuro */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = primaryOrange)
                        ) {
                            Text(
                                text = "Enviar instruções",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Botão Voltar ao Login
                        Text(
                            text = "Voltar para o Login",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            modifier = Modifier.clickable { navController.popBackStack() } // Volta a tela!
                        )
                    }
                }

                // A Logo
                Image(
                    painter = painterResource(R.drawable.logo), // Troque pela sua logo
                    contentDescription = "Logo Tagdoo",
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.White, CircleShape)
                        .padding(6.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}