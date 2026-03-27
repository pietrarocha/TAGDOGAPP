package com.example.apptagdog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apptagdog.R
import com.example.apptagdog.components.InputLabel // <-- Puxando o seu componente novo!

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CadastroCachorroScreen(navController: NavController) {
    // Definindo as cores baseadas no seu design
    val primaryOrange = Color(0xFFD17B42)
    val darkText = Color(0xFF1C2434)
    val lightGray = Color(0xFFF3F4F6)
    val darkBlue = Color(0xFF1B243B)

    // Estados dos campos de texto simples
    var nome by remember { mutableStateOf("") }
    var raca by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }

    // Estados para o Dropdown de Sexo
    val opcoesSexo = listOf("Macho", "Fêmea")
    var expandidoSexo by remember { mutableStateOf(false) }
    var sexoSelecionado by remember { mutableStateOf("") }

    // Estados para o Dropdown de Porte
    val opcoesPorte = listOf("Pequeno", "Médio", "Grande")
    var expandidoPorte by remember { mutableStateOf(false) }
    var porteSelecionado by remember { mutableStateOf("") }

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
            painter = painterResource(R.drawable.logo), // Confirme se o nome da imagem é esse mesmo
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
                    text = "Dados do seu Pet",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quase lá! Agora as informações do peludo.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Campo: Nome
                InputLabel(text = "Nome", color = primaryOrange)
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

                // Campo: Raça
                InputLabel(text = "Raça", color = primaryOrange)
                OutlinedTextField(
                    value = raca,
                    onValueChange = { raca = it },
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

                // Campos: Sexo e Porte (Lado a lado)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // --- Dropdown: Sexo ---
                    Column(modifier = Modifier.weight(1f)) {
                        InputLabel(text = "Sexo", color = primaryOrange)
                        ExposedDropdownMenuBox(
                            expanded = expandidoSexo,
                            onExpandedChange = { expandidoSexo = !expandidoSexo }
                        ) {
                            OutlinedTextField(
                                value = sexoSelecionado,
                                onValueChange = { },
                                readOnly = true,
                                placeholder = { Text("Selecione", color = Color.Gray, fontSize = 14.sp) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoSexo) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = primaryOrange,
                                    unfocusedBorderColor = Color.LightGray,
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expandidoSexo,
                                onDismissRequest = { expandidoSexo = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                opcoesSexo.forEach { opcao ->
                                    DropdownMenuItem(
                                        text = { Text(opcao) },
                                        onClick = {
                                            sexoSelecionado = opcao
                                            expandidoSexo = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // --- Dropdown: Porte ---
                    Column(modifier = Modifier.weight(1f)) {
                        InputLabel(text = "Porte", color = primaryOrange)
                        ExposedDropdownMenuBox(
                            expanded = expandidoPorte,
                            onExpandedChange = { expandidoPorte = !expandidoPorte }
                        ) {
                            OutlinedTextField(
                                value = porteSelecionado,
                                onValueChange = { },
                                readOnly = true,
                                placeholder = { Text("Selecione", color = Color.Gray, fontSize = 14.sp) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoPorte) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = primaryOrange,
                                    unfocusedBorderColor = Color.LightGray,
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expandidoPorte,
                                onDismissRequest = { expandidoPorte = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                opcoesPorte.forEach { opcao ->
                                    DropdownMenuItem(
                                        text = { Text(opcao) },
                                        onClick = {
                                            porteSelecionado = opcao
                                            expandidoPorte = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo: Observações / Alergias
                InputLabel(text = "Observações / Alergias", color = primaryOrange)
                OutlinedTextField(
                    value = observacoes,
                    onValueChange = { observacoes = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryOrange,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedContainerColor = lightGray,
                        focusedContainerColor = Color.White
                    ),
                    placeholder = { Text("Ex: Alérgico a frango, dócil...", color = Color.Gray, fontSize = 14.sp) }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botões: Voltar e Finalizar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() }, // Retorna para a tela anterior
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Gray
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Text("Voltar", fontSize = 14.sp)
                    }

                    Button(
                        onClick = { /* Aqui vai a ação de salvar o pet e ir pra próxima tela */ },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
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
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCadastroCachorroScreen() {
    MaterialTheme {
        CadastroCachorroScreen(navController = rememberNavController())
    }
}
