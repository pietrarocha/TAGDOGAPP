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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ComplementoCadastroScreen(navController: NavController) {
    // Cores do design
    val primaryOrange = Color(0xFFD17B42)
    val darkText = Color(0xFF1C2434)
    val lightGray = Color(0xFFF3F4F6)
    val darkBlue = Color(0xFF1B243B)

    // Estados dos campos de texto
    var cpf by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var rua by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var cidadeUf by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Logo
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

        // Card Branco principal (Agora ele não estica até o final)
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), // Voltei para 16.dp para as bordas respirarem melhor
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título e Subtítulo
                Text(
                    text = "Onde você mora?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Isso nos ajuda a organizar os atendimentos.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CPF
                CustomTextField(
                    value = cpf,
                    onValueChange = { cpf = it },
                    label = "CPF",
                    primaryOrange = primaryOrange,
                    lightGray = lightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Telefone
                CustomTextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    label = "Telefone",
                    primaryOrange = primaryOrange,
                    lightGray = lightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // CEP
                CustomTextField(
                    value = cep,
                    onValueChange = { cep = it },
                    label = "CEP",
                    primaryOrange = primaryOrange,
                    lightGray = lightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rua e Número (Lado a lado)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CustomTextField(
                        value = rua,
                        onValueChange = { rua = it },
                        label = "Rua / Avenida",
                        primaryOrange = primaryOrange,
                        lightGray = lightGray,
                        modifier = Modifier.weight(2f)
                    )
                    CustomTextField(
                        value = numero,
                        onValueChange = { numero = it },
                        label = "Número",
                        primaryOrange = primaryOrange,
                        lightGray = lightGray,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Bairro e Cidade/UF (Lado a lado)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CustomTextField(
                        value = bairro,
                        onValueChange = { bairro = it },
                        label = "Bairro",
                        primaryOrange = primaryOrange,
                        lightGray = lightGray,
                        modifier = Modifier.weight(1f)
                    )
                    CustomTextField(
                        value = cidadeUf,
                        onValueChange = { cidadeUf = it },
                        label = "Cidade / UF",
                        primaryOrange = primaryOrange,
                        lightGray = lightGray,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Espaçamento até os botões
                Spacer(modifier = Modifier.height(24.dp))

                // Botões menores e centralizados
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(130.dp)
                            .height(44.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Text("Voltar", fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { /* Ação Finalizar ou Próxima Etapa */ },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(130.dp)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryOrange)
                    ) {
                        Text("Próximo", fontSize = 14.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

// Componente Reutilizável para os Campos de Texto
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    primaryOrange: Color,
    lightGray: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = primaryOrange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp, start = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryOrange,
                unfocusedBorderColor = Color.LightGray,
                unfocusedContainerColor = lightGray,
                focusedContainerColor = Color.White
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewComplementoCadastroScreen() {
    MaterialTheme {
        ComplementoCadastroScreen(navController = rememberNavController())
    }
}