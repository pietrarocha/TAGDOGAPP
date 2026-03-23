package com.example.apptagdog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptagdog.R


@Composable
fun TagdogLoginScreen() {
    // Definindo as cores baseadas no seu design
    val primaryOrange = Color(0xFFD17B42)
    val backgroundGray = Color(0xFFF5F6F8)
    val darkText = Color(0xFF1C2434)
    val lightGray = Color(0xFFF3F4F6)
    // ADICIONADO: Nova cor de fundo azul escuro
    val darkBlue = Color(0xFF1B243B)

    // Estados dos campos de texto
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            // ALTERADO: Usando a nova cor de fundo azul escuro
            .background(darkBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 1. Logo (Ajustado para usar Image)
        // Certifique-se de colocar a imagem da sua logo na pasta res/drawable do Android Studio
        Image(
            // Substitua 'R.drawable.logo_tagdog' pelo nome da sua imagem, ex: painterResource(R.drawable.sua_logo)
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Tagdog",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "TAGDOG",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            // ALTERADO: Cor do texto para branco para contraste com o fundo escuro
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
                    text = "Vamos começar com as informações\nbásicas de acesso.",
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
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Ver email",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryOrange,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedContainerColor = lightGray,
                        focusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dropdown Simulado: Sou Funcionário
                OutlinedTextField(
                    value = "Sou Tutor",
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Expandir",
                            tint = darkText
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botões: Voltar e Próxima Etapa
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { /* Ação Voltar */ },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Gray
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Text("Voltar", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { /* Ação Próxima Etapa */ },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryOrange
                        )
                    ) {
                        Text("Próxima Etapa", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Link de Suporte Técnico no rodapé
        TextButton(onClick = { /* Ação de Suporte */ }) {

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Componente auxiliar para os rótulos laranjas acima dos campos
@Composable
fun InputLabel(text: String, color: Color) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, start = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTagdogScreen() {
    MaterialTheme {
        TagdogLoginScreen()
    }
}