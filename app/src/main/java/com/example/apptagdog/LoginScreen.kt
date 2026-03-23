package com.example.apptagdog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Lembre-se de importar o seu R aqui, ex: import com.seuapp.R

@Composable
fun TagdooLoginScreen(onLoginClick: () -> Unit) {
    // Cores do layout
    val primaryOrange = Color(0xFFD37D41)
    val backgroundGray = Color(0xFFF5F6F8)
    val darkText = Color(0xFF1B243B)

    // Estados dos campos
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisible by remember { mutableStateOf(false) }

    // Box principal que ocupa toda a tela
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
    ) {
        // 1. Imagem de Fundo (Os Cachorros)
        // Coloque a foto dos cachorros na pasta drawable e mude o R.drawable abaixo
        Image(
            painter = painterResource(R.drawable.cachorros), // Troque por R.drawable.foto_cachorros
            contentDescription = "Fundo de cachorros",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp) // Altura da imagem de fundo
        )

        // 2. Conteúdo Principal (Card e Textos)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Empurra o card para baixo para revelar a imagem de fundo
            Spacer(modifier = Modifier.height(220.dp))

            // Box para fazer a logo sobrepor o Card Branco
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
                        .padding(top = 45.dp) // Espaço exato para a logo encaixar na borda
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp)) // Espaço abaixo da logo

                        // Títulos
                        Text(
                            text = "Bem-vindo",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = darkText
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Acesse sua conta para continuar",
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

                        Spacer(modifier = Modifier.height(16.dp))

                        // Campo Senha
                        OutlinedTextField(
                            value = senha,
                            onValueChange = { senha = it },
                            placeholder = { Text("Senha", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            visualTransformation = if (senhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val image = if (senhaVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                                IconButton(onClick = { senhaVisible = !senhaVisible }) {
                                    Icon(imageVector = image, contentDescription = "Mostrar senha", tint = Color.Gray)
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryOrange,
                                unfocusedBorderColor = Color.LightGray,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Botão Entrar
                        Button(
                            onClick = { onLoginClick() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = primaryOrange)
                        ) {
                            Text(
                                text = "Entrar",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Esqueci minha senha
                        Text(
                            text = "Esqueci minha senha",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = primaryOrange,
                            modifier = Modifier.clickable { /* Ação de recuperar senha */ }
                        )
                    }
                }

                // A Logo (Fica sobre o Card por estar depois dele dentro do Box)
                Image(
                    painter = painterResource(R.drawable.logo),
                    // Troque por R.drawable.sua_logo
                    contentDescription = "Logo Tagdoo",
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.White, CircleShape) // Cria a borda branca ao redor da logo
                        .padding(6.dp) // Espessura da borda branca
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Rodapé
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTagdooLoginScreen() {
    MaterialTheme {
        TagdooLoginScreen({})
    }
}