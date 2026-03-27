package com.example.apptagdog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TagdooHomeScreen(navController: NavController) {
    // Cores baseadas no seu design
    val primaryOrange = Color(0xFFD37D41)
    val darkBlue = Color(0xFF1B243B)
    val backgroundGray = Color(0xFFF5F6F8)
    val lightOrangeBg = Color(0xFFFDF0E7)
    val darkText = Color(0xFF1B243B)
    val grayText = Color(0xFF888888)

    Scaffold(
        containerColor = backgroundGray,
        floatingActionButton = {
            // Botão Flutuante "Novo Check-In"
            Button(
                onClick = { /* Ação de Check-in */ },
                colors = ButtonDefaults.buttonColors(containerColor = primaryOrange),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = "Novo Cadastro",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        bottomBar = {
            // Barra de Navegação Inferior
            HomeBottomNav(primaryOrange = primaryOrange, grayText = grayText)
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White) // Fundo branco no topo para o cabeçalho
        ) {

            // 1. Topo com Voltar, Serviços e Perfil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            // Substitua "rota_de_login" pelo nome exato da sua rota no NavHost
                            navController.navigate("login") {
                                // Essa parte garante que a pilha de telas seja limpa,
                                // evitando que o usuário aperte "voltar" no celular e volte para cá
                                popUpTo(0) { inclusive = true }
                            }
                        }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Voltar",
                            tint = darkText,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Título Centralizado
                Text(
                    text = "Serviços",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )

                // Ícone de Perfil (Alinhado à direita)
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFFE0E0E0), CircleShape)
                        .align(Alignment.CenterEnd)
                        .clickable { /* Ação do Perfil */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // 2. Área Azul e Cartões Sobrepostos
            Box(modifier = Modifier.fillMaxSize()) {

                // Fundo Azul Escuro
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(darkBlue)
                )

                // Recipiente Cinza/Branco com Cantos Arredondados (Sobreposto ao Azul)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp) // Sobe o cartão por cima do azul
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(backgroundGray)
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 70.dp), // Adicionado padding inferior para compensar o FAB e ajudar na centralização
                    verticalArrangement = Arrangement.Center // CENTRALIZA OS CARTÕES NA TELA
                ) {

                    // Cartão: Rastreamento GPS
                    ServiceCardItem(
                        title = "Rastreamento GPS",
                        description = "Rastreie o seu PET.",
                        icon = Icons.Default.LocationOn,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Cartão: Perfil (antigo Banco de Tutores)
                    ServiceCardItem(
                        title = "Perfil",
                        description = "Acesse os seus dados.",
                        icon = Icons.Default.Search,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText
                    )
                }
            }
        }
    }
}

// --- Componentes Reutilizáveis ---

@Composable
fun ServiceCardItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconColor: Color,
    darkText: Color
) {
    Card(
        shape = RoundedCornerShape(20.dp), // Deixei os cantos um pouco mais redondos
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Aumentei levemente a sombra
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Ação do cartão */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp), // AUMENTEI O PADDING INTERNO AQUI
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp) // AUMENTEI A CAIXA DO ÍCONE (era 50)
                    .background(iconBgColor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(28.dp) // AUMENTEI O ÍCONE (era 24)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp, // AUMENTEI O TÍTULO (era 16)
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = description,
                    fontSize = 13.sp, // AUMENTEI A DESCRIÇÃO (era 11)
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp // Ajustei a altura da linha
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ir",
                tint = iconColor,
                modifier = Modifier.size(28.dp) // Aumentei a setinha também
            )
        }
    }
}

@Composable
fun HomeBottomNav(primaryOrange: Color, grayText: Color) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // DEIXAMOS APENAS INÍCIO, MAPA E PERFIL AQUI:
            HomeBottomNavItem(icon = Icons.Default.Home, label = "Inicio", isSelected = true, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
            HomeBottomNavItem(icon = Icons.Default.LocationOn, label = "Mapa", isSelected = false, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
            HomeBottomNavItem(icon = Icons.Default.Person, label = "Perfil", isSelected = false, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun HomeBottomNavItem(icon: ImageVector, label: String, isSelected: Boolean, activeColor: Color, inactiveColor: Color, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { /* Navegação */ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(3.dp)
                .background(if (isSelected) activeColor else Color.Transparent)
        )
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) activeColor else inactiveColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) activeColor else inactiveColor
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTagdooHomeScreen() {
    MaterialTheme {
        TagdooHomeScreen(navController = rememberNavController())
    }
}