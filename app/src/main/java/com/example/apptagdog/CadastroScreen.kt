package com.example.apptagdog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apptagdog.R
import com.example.apptagdog.components.InputLabel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CadastroScreen(navController: NavController) {
    // Definindo as cores baseadas no seu design
    val primaryOrange = Color(0xFFD17B42)
    val lightGray = Color(0xFFF3F4F6)
    val darkText = Color(0xFF1C2434)
    val darkBlue = Color(0xFF1B243B)

    // Estados dos campos de texto
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    // Estados para a senha
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }

    // Estados para o Dropdown
    val opcoesDePerfil = listOf("Sou Tutor", "Sou Funcionário")
    var expandido by remember { mutableStateOf(false) }
    // ALTERAÇÃO 1: Começa vazio para não forçar a seleção
    var perfilSelecionado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 1. Logo
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Tagdog",
            modifier = Modifier
                .size(90.dp)
                .background(Color.White, CircleShape)
                .padding(6.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "TAGDOG",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))

        // 2. Card Branco principal
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título e Subtítulo
                Text(
                    text = "Bem-vindo ao\nTAGDOG",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Vamos começar com as informações \n básicas de acesso.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Campo: Nome Completo
                InputLabel(text = "Nome Completo", color = primaryOrange)
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryOrange,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedContainerColor = lightGray,
                        focusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo: E-mail
                InputLabel(text = "E-mail", color = primaryOrange)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryOrange,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedContainerColor = lightGray,
                        focusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo: Criar Senha
                InputLabel(text = "Criar Senha", color = primaryOrange)
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (senhaVisivel) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                            Icon(
                                imageVector = image,
                                contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha",
                                tint = Color.Gray
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryOrange,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedContainerColor = lightGray,
                        focusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ALTERAÇÃO 2: Adicionando o Label para o Tipo de Perfil
                InputLabel(text = "Tipo de Perfil", color = primaryOrange)

                // Dropdown: Seleção de Perfil
                ExposedDropdownMenuBox(
                    expanded = expandido,
                    onExpandedChange = { expandido = !expandido },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = perfilSelecionado,
                        onValueChange = { },
                        readOnly = true,
                        // ALTERAÇÃO 3: Placeholder para guiar o usuário quando estiver vazio
                        placeholder = { Text("Selecione", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryOrange,
                            unfocusedBorderColor = Color.LightGray,
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expandido,
                        onDismissRequest = { expandido = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        opcoesDePerfil.forEach { opcao ->
                            DropdownMenuItem(
                                text = { Text(opcao) },
                                onClick = {
                                    perfilSelecionado = opcao
                                    expandido = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botões
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(140.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Gray
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Text("Voltar", fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { /* Ação Próxima Etapa */ },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(140.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryOrange
                        )
                    ) {
                        Text("Próximo", fontSize = 14.sp, color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = { /* Ação de Suporte */ }) {
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTagdogScreen() {
    MaterialTheme {
        CadastroScreen(navController = rememberNavController())
    }
}